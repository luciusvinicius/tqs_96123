package ies.hw.hw1.http;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;
import ies.hw.hw1.models.DataOutput;

@Service
public class API2 implements BasicAPI {
    
    private static final String BASE_URL = "https://covid-19-statistics.p.rapidapi.com/";
    private static final String REPORTS_URL = BASE_URL + "reports";
    private static final String REGIONS_URL = BASE_URL + "regions";
    private static final String HEADER_HOST = "covid-19-statistics.p.rapidapi.com";
    private static final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

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

    public JSONObject getAllCountries() throws ParseException, InterruptedException {
        try {
            return doRequest(REGIONS_URL);
        }
        catch (IOException e) {
            return new JSONObject(Collections.emptyMap());
        }
    }

    public Cache getCacheInfo() {
        return cache.getCache();
    }

    public List<DataOutput> getCountryByRegion(String country) throws ParseException, InterruptedException {
        try {
            JSONObject response = doRequest(REPORTS_URL + "?region_name=" + country);
            DataOutput data = new DataOutput(response);
            List<DataOutput> responseGroup = new ArrayList<>();
            responseGroup.add(data);
            JSONArray dataJson = (JSONArray) response.get("data");
            if (dataJson.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return responseGroup;
        }
        catch (IOException | IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

    public List<DataOutput> getCountryByRegionAndDate(String country, String startDate, String endDate) throws ParseException, InterruptedException {
        try {

            return filterByDateRange(country, startDate, endDate);
        }
        catch (IOException | IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

    private List<DataOutput> filterByDateRange(String country, String startDate, String endDate) throws IOException, InterruptedException, ParseException {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DataOutput> responseGroup = new ArrayList<>();
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1))
        {
            JSONObject response = doRequest(REPORTS_URL + "?region_name=" + country + "&date=" + date.toString());
            DataOutput data = new DataOutput(response);
            responseGroup.add(data);
        }

        return responseGroup;
    }  

    private JSONObject doRequest(String url) throws IOException, InterruptedException, ParseException {
        return client.doRequest(url, cache, HEADER_HOST, HEADER_KEY);
    }




}
