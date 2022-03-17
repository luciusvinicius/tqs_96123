package ies;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.http.ParseException;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

/**
 * @author ico
 */
public class AddressResolver {

    private ISimpleHttpClient httpClient;

    public AddressResolver(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public Optional<Address> findAddressForLocation(double latitude, double longitude) throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {


        String apiKey = "uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ";


        URIBuilder uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", apiKey);
        uriBuilder.addParameter("location", (new Formatter()).format(Locale.US, "%.6f,%.6f", latitude, longitude).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");


        System.err.println(" url is --> " + uriBuilder.build().toString() + " <--");

        String response = this.httpClient.doHttpGet(uriBuilder.build().toString());

        System.out.println("JSON is: >" + response + "<");

        // get parts from response till reaching the address
        JSONObject obj = (JSONObject) new JSONParser().parse(response);
        obj = (JSONObject) ((JSONArray) obj.get("results")).get(0);
        JSONArray locations = (JSONArray) obj.get("locations");

        if (locations.isEmpty()) {
            return Optional.empty();
        }

        JSONObject address = (JSONObject) locations.get(0);

        String road = (String) address.get("street");
        String city = (String) address.get("adminArea5");
        String state = (String) address.get("adminArea3");
        String zip = (String) address.get("postalCode");
        return Optional.of(new Address(road, city, state, zip, null));
    }
}
