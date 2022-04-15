package ies.hw.hw1.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ies.hw.hw1.http.BasicHttpClient;
import ies.hw.hw1.models.Cache;

@Service
public class CovidService {

    private BasicHttpClient client = new BasicHttpClient();
    
    public JSONObject getAllCountries() throws ParseException {
        return client.getAllCountries();
    }

    public JSONObject getStatsByContinent(String continent) throws ParseException {
        return client.getCountryByRegion(continent);
    }

    public JSONObject getStatsByCountry(String name) throws ParseException {
        return client.getCountryByRegion(name);
    }

    public JSONObject getStatsByCountryAndDate(String name, String date) throws ParseException {
        return client.getCountryByRegionAndDate(name, date);
    }

    public Cache getCacheInfo() {
        return client.getCacheInfo();
    }
}
