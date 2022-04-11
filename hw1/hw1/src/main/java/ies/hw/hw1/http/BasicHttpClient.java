package ies.hw.hw1.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.google.gson.Gson;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasicHttpClient extends Thread {
    
    private static final String BASE_URL = "https://covid-193.p.rapidapi.com/";
    private static final String STATISTICS_URL = BASE_URL + "statistics";
    private static final String HISTORY_URL = BASE_URL + "history";
    private static final String COUNTRIES_URL = BASE_URL + "countries";
    private static final String HEADER_HOST = "covid-193.p.rapidapi.com";
    private static final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    // private static HashMap<String, ResponseEntity<String>> cacheResponse = new HashMap<>();
    // private static HashMap<String, Integer> cacheTimesUsed = new HashMap<>();
    private static WeakConcurrentHashMap<String, ResponseEntity<String>> cache = new WeakConcurrentHashMap<>();

    private static int cacheHits = 0;
    private static int cacheMisses = 0;


    public static ResponseEntity<String> getAllCountries() {
        try {
            return doRequest(COUNTRIES_URL);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResponseEntity<String> getCountryByRegion(String country) {
        try {

            return doRequest(HISTORY_URL + "?country=" + country);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResponseEntity<String> getCountryByRegionAndDate(String country, String date) {
        try {
            
            return doRequest(HISTORY_URL + "?country=" + country + "&day=" + date);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ResponseEntity<String> doRequest(String uri) throws IOException, InterruptedException {

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
        ResponseEntity<String> resp = new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.statusCode()));

        cache.put(uri, resp); // Insert response entity on cache

        return resp;
    }    
}
