package ies.hw.hw1.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;

@Service
public class BasicHttpClient extends Thread {
    
    private final String BASE_URL = "https://covid-193.p.rapidapi.com/";
    private final String STATISTICS_URL = BASE_URL + "statistics";
    private final String HISTORY_URL = BASE_URL + "history";
    private final String COUNTRIES_URL = BASE_URL + "countries";
    private final String HEADER_HOST = "covid-193.p.rapidapi.com";
    private final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    // private  HashMap<String, ResponseEntity<String>> cacheResponse = new HashMap<>();
    // private  HashMap<String, Integer> cacheTimesUsed = new HashMap<>();
    private WeakConcurrentHashMap<String, JSONObject> cache = new WeakConcurrentHashMap<>();

    public Cache getCacheInfo() {
        return cache.getCache();
    }

    public JSONObject getAllCountries() throws ParseException {
        try {
            return doRequest(COUNTRIES_URL);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getCountryByRegion(String country) throws ParseException {
        try {

            return doRequest(HISTORY_URL + "?country=" + country);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getCountryByRegionAndDate(String country, String date) throws ParseException {
        try {
            
            return doRequest(HISTORY_URL + "?country=" + country + "&day=" + date);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
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
