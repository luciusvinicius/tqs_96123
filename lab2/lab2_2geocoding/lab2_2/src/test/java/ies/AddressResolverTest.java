package ies;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AddressResolverTest {

    @Mock
    ISimpleHttpClient client;

    private AddressResolver solver;

    private final String correctResult ="{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-8.658}},\"locations\":[{\"street\":\"Parque Estacionamento da Reitoria - Univerisdade de Aveiro\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3810-193\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"displayLatLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&type=map&size=225,160&locations=40.6318025,-8.657881|marker-sm-50318A-1&scalebar=true&zoom=15&rand=-1293581016\",\"roadMetadata\":null}]}]}";
    private final String wrongResult = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":4000.6318,\"lng\":-800.658}},\"locations\":[]}]}";


    @BeforeEach
    public void setUp() throws IOException {
        client = mock(TqsBasicHttpClient.class);

        solver = new AddressResolver(client);

        when(client.doHttpGet("http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=40.631800%2C-8.658000&includeRoadMetadata=true"))
        .thenReturn(correctResult);

        when(client.doHttpGet("http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=4000.631800%2C-800.658000&includeRoadMetadata=true"))
        .thenReturn(wrongResult);

    }

    @Test
    void testFindExistentAddressForLocation() throws ParseException, URISyntaxException, IOException, org.json.simple.parser.ParseException {


        double lat = 40.6318;
        double lng = -8.658;

        Optional<Address> add = solver.findAddressForLocation(lat,lng);

        assertTrue(add.isPresent());
    }

    @Test
    void testFindNonExistentAddressForLocation()  throws ParseException, URISyntaxException, IOException, org.json.simple.parser.ParseException {

        double lat = 4000.6318;
        double lng = -800.658;

        Optional<Address> add = solver.findAddressForLocation(lat,lng);

        assertTrue(add.isEmpty());
    }

}
