package ies.hw.hw1.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;

@Service
public class Client2 extends Thread implements BasicHttpClient {
    
    private final String BASE_URL = "https://covid-19-statistics.p.rapidapi.com/";
    private final String REPORTS_URL = BASE_URL + "reports";
    private final String REGIONS_URL = BASE_URL + "regions";
    private final String HEADER_HOST = "covid-19-statistics.p.rapidapi.com";
    private final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";
    
    private WeakConcurrentHashMap<String, JSONObject> cache = new WeakConcurrentHashMap<>();

    public JSONObject getAllCountries() throws ParseException {
        try {
            return doRequest(REGIONS_URL);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cache getCacheInfo() {
        return cache.getCache();
    }

    public List<JSONObject> getCountryByRegion(String country) throws ParseException {
        try {
            JSONObject response = doRequest(REPORTS_URL + "?region_name=" + country);
            List<JSONObject> response_group = new ArrayList<>();
            response_group.add(response);
            return response_group;
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<JSONObject> getCountryByRegionAndDate(String country, String startDate, String endDate) throws ParseException {
        try {

            return filterByDateRange(country, startDate, endDate);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<JSONObject> filterByDateRange(String country, String startDate, String endDate) throws IOException, InterruptedException, ParseException {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<JSONObject> response_group = new ArrayList<>();
        
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1))
        {
            JSONObject response = doRequest(REPORTS_URL + "?region_name=" + country + "&date=" + date.toString());
            response_group.add(response);
        }

        return response_group;
    }  


    private JSONObject doRequest(String uri) throws IOException, InterruptedException, ParseException {

        // Verify if response entity is present on cache
        if (cache.hitOrMiss(uri)) {
            System.out.println("Cache used for url: " + uri);
            return cache.get(uri);
        }

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .header("X-RapidAPI-Host", HEADER_HOST)
        .header("X-RapidAPI-Key", HEADER_KEY)
        .header("Content-Type", "application/json")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject resp = (JSONObject) new JSONParser().parse(response.body());

        cache.put(uri, resp); // Insert response entity on cache

        return resp;
    }    

}
