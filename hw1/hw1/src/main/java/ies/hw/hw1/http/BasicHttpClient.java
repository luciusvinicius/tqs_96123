package ies.hw.hw1.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BasicHttpClient {
    
    private static final String BASE_URL = "https://covid-193.p.rapidapi.com/";
    private static final String STATISTICS_URL = BASE_URL + "statistics";
    private static final String HISTORY_URL = BASE_URL + "history";
    private static final String COUNTRIES_URL = BASE_URL + "countries";
    private static final String HEADER_HOST = "covid-193.p.rapidapi.com";
    private static final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    public static HttpResponse<String> getAllCountries() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(COUNTRIES_URL))
            .header("X-RapidAPI-Host", HEADER_HOST)
            .header("X-RapidAPI-Key", HEADER_KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response;
    }
    
}
