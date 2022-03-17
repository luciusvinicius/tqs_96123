package ies;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressResolverIT {
    private ISimpleHttpClient client;

    private AddressResolver solver;



    @BeforeEach
    public void setUp() {

        client = new TqsBasicHttpClient();

        solver = new AddressResolver(client);
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
