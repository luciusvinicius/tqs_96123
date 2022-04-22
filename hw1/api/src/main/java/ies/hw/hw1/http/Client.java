package ies.hw.hw1.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class Client {

    private static final Logger logger = LogManager.getLogger(Client.class);

    public JSONObject doRequest(String uri, WeakConcurrentHashMap<String, JSONObject> cache, 
    String host, String key) throws IOException, InterruptedException, ParseException {


        // Verify if response entity is present on cache
        if (cache.hitOrMiss(uri)) {
            
            if (logger.isInfoEnabled())
                logger.info(MessageFormat.format("Cache used for url: {0}", uri));
            return cache.get(uri);
        }

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .header("X-RapidAPI-Host", host)
        .header("X-RapidAPI-Key", key)
        .header("Content-Type", "application/json")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject resp = (JSONObject) new JSONParser().parse(response.body());

        cache.put(uri, resp); // Insert response entity on cache

        return resp;
    }    
    
    
}
