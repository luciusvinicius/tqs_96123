package ies.hw.hw1.http;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;

@Service
public interface BasicHttpClient {

    public Cache getCacheInfo();
    public JSONObject getAllCountries() throws ParseException;
    public JSONObject getCountryByRegion(String country) throws ParseException;
    public JSONObject getCountryByRegionAndDate(String country, String date) throws ParseException;
}
