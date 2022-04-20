package ies.hw.hw1.http;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.Cache;
import ies.hw.hw1.models.DataOutput;

@Service
public interface BasicAPI {

    public Cache getCacheInfo() throws InterruptedException;
    public JSONObject getAllCountries() throws ParseException, InterruptedException;
    public List<DataOutput> getCountryByRegion(String country) throws ParseException, InterruptedException;
    public List<DataOutput> getCountryByRegionAndDate(String country, String startDate, String endDate) throws ParseException, InterruptedException;
}
