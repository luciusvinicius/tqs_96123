package ies.hw.hw1.http;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;
import ies.hw.hw1.models.DataOutput;

@Service
public abstract class BasicAPI {

    private String listCountriesUrl;
    private String countryStatsUrl;
    private String headerHost;
    private String headerKey;

    private WeakConcurrentHashMap<String, JSONObject> cache = new WeakConcurrentHashMap<>();

    private Client client = new Client();

    public BasicAPI(String listCountriesUrl, String countryStatsUrl, String headerHost, String headerKey) {
        this.listCountriesUrl = listCountriesUrl;
        this.countryStatsUrl = countryStatsUrl;
        this.headerHost = headerHost;
        this.headerKey = headerKey;
    }

    public JSONObject getAllCountries() throws ParseException, InterruptedException {
        try {
            return doRequest(listCountriesUrl);
        }
        catch (IOException e) {
            return new JSONObject(Collections.emptyMap());
        }
    }

    public List<DataOutput> getCountryByRegion(String country, boolean isApi1) throws ParseException, InterruptedException {
        try {
            JSONObject response = doRequest(countryStatsUrl + "?country=" + country + "&day=" + LocalDate.now().toString());
            return getCountryByJsonObject(response, isApi1);
        }
        catch (IOException | IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

    public List<DataOutput> getCountryByJsonObject(JSONObject obj, boolean isApi1) {
        List<DataOutput> responseGroup = new ArrayList<>();
        DataOutput data; 
        if (isApi1)
            data = new DataOutput(obj, true);
        else
            data = new DataOutput(obj);
        responseGroup.add(data);
        return responseGroup;
    }

    public JSONObject getCountryByUrl(String url) throws ParseException, InterruptedException {
        try {
            return doRequest(url);
        }
        catch (IOException | IndexOutOfBoundsException e) {
            return new JSONObject(Collections.emptyMap());
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

    public List<DataOutput> filterByDateRange(String country, String startDate, String endDate) throws IOException, InterruptedException, ParseException {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DataOutput> responseGroup = new ArrayList<>();
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1))
        {
            JSONObject response = doRequest(countryStatsUrl + "?country=" + country + "&day=" + date.toString());
            DataOutput data = new DataOutput(response, true);
            responseGroup.add(data);
        }

        return responseGroup;
    }

    private JSONObject doRequest(String url) throws IOException, InterruptedException, ParseException {
        return client.doRequest(url, cache, headerHost, headerKey);
    }

    // Setters and Getters

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getListCountriesUrl() {
        return listCountriesUrl;
    }

    public void setListCountriesUrl(String listCountriesUrl) {
        this.listCountriesUrl = listCountriesUrl;
    }

    public String getCountryStatsUrl() {
        return countryStatsUrl;
    }

    public void setCountryStatsUrl(String countryStatsUrl) {
        this.countryStatsUrl = countryStatsUrl;
    }

    public String getHeaderHost() {
        return headerHost;
    }

    public void setHeaderHost(String headerHost) {
        this.headerHost = headerHost;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public WeakConcurrentHashMap<String, JSONObject> getConcurrentCash() {
        return cache;
    }

    public Cache getCacheInfo() {
        return cache.getCache();
    }
}
