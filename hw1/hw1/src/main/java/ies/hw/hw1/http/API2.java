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
public class API2 extends Thread implements BasicAPI {
    
    private final String BASE_URL = "https://covid-19-statistics.p.rapidapi.com/";
    private final String REPORTS_URL = BASE_URL + "reports";
    private final String REGIONS_URL = BASE_URL + "regions";
    private final String HEADER_HOST = "covid-19-statistics.p.rapidapi.com";
    private final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    public String getHost() {
        return HEADER_HOST;
    }

    public String getKey() {
        return HEADER_KEY;
    }

    private WeakConcurrentHashMap<String, JSONObject> cache = new WeakConcurrentHashMap<>();

    private Client client = new Client();

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public WeakConcurrentHashMap<String, JSONObject> getConcurrentCash() {
        return cache;
    }

    public JSONObject getAllCountries() throws ParseException {
        try {
            return doRequest(REGIONS_URL);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Sussy error");
            return null;
        }
    }

    public Cache getCacheInfo() {
        return cache.getCache();
    }

    public List<DataOutput> getCountryByRegion(String country) throws ParseException {
        try {
            JSONObject response = doRequest(REPORTS_URL + "?region_name=" + country);
            DataOutput data = new DataOutput(response, false);
            List<DataOutput> response_group = new ArrayList<>();
            response_group.add(data);
            return response_group;
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DataOutput> getCountryByRegionAndDate(String country, String startDate, String endDate) throws ParseException {
        try {

            return filterByDateRange(country, startDate, endDate);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<DataOutput> filterByDateRange(String country, String startDate, String endDate) throws IOException, InterruptedException, ParseException {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DataOutput> response_group = new ArrayList<>();
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1))
        {
            JSONObject response = doRequest(REPORTS_URL + "?region_name=" + country + "&date=" + date.toString());
            DataOutput data = new DataOutput(response, false);
            response_group.add(data);
        }

        return response_group;
    }  

    private JSONObject doRequest(String url) throws IOException, InterruptedException, ParseException {
        return client.doRequest(url, cache, HEADER_HOST, HEADER_KEY);
    }




}
