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
import ies.hw.hw1.models.DataOutput;

@Service
public class API1 extends Thread implements BasicAPI {
    
    private final String BASE_URL = "https://covid-193.p.rapidapi.com/";
    private final String STATISTICS_URL = BASE_URL + "statistics";
    private final String HISTORY_URL = BASE_URL + "history";
    private final String COUNTRIES_URL = BASE_URL + "countries";
    private final String HEADER_HOST = "covid-193.p.rapidapi.com";
    private final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    private WeakConcurrentHashMap<String, JSONObject> cache = new WeakConcurrentHashMap<>();

    private Client client = new Client();

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Cache getCacheInfo() {
        return cache.getCache();
    }

    public JSONObject getAllCountries() throws ParseException {
        try {
            return doRequest(COUNTRIES_URL);
        }
        catch (IOException | InterruptedException e) {
            return null;
        }
    }

    public List<DataOutput> getCountryByRegion(String country) throws ParseException {
        try {
            JSONObject response = doRequest(HISTORY_URL + "?country=" + country + "&day=" + LocalDate.now().toString());
            List<DataOutput> response_group = new ArrayList<>();
            DataOutput data = new DataOutput(response, true);
            response_group.add(data);
            return response_group;
        }
        catch (IOException | InterruptedException | IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

    public List<DataOutput> getCountryByRegionAndDate(String country, String startDate, String endDate) throws ParseException {
        try {
            return filterByDateRange(country, startDate, endDate);
        }
        catch (IOException | InterruptedException | IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

    private List<DataOutput> filterByDateRange(String country, String startDate, String endDate) throws IOException, InterruptedException, ParseException {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DataOutput> response_group = new ArrayList<>();
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1))
        {
            JSONObject response = doRequest(HISTORY_URL + "?country=" + country + "&day=" + date.toString());
            DataOutput data = new DataOutput(response, true);
            response_group.add(data);
        }

        return response_group;
    }

    private JSONObject doRequest(String url) throws IOException, InterruptedException, ParseException {
        return client.doRequest(url, cache, HEADER_HOST, HEADER_KEY);
    }

}
