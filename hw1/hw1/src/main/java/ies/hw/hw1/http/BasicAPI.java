package ies.hw.hw1.http;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;

@Service
public interface BasicAPI {

    public Cache getCacheInfo();
    public JSONObject getAllCountries() throws ParseException;
    public List<JSONObject> getCountryByRegion(String country) throws ParseException;
    public List<JSONObject> getCountryByRegionAndDate(String country, String startDate, String endDate) throws ParseException;
}
