package ies.hw.hw1.http;

import org.springframework.stereotype.Service;

import ies.hw.hw1.models.DataOutput;

import java.util.List;

import org.json.simple.parser.ParseException;

@Service
public class API1 extends BasicAPI {
    
    private static final String BASE_URL = "https://covid-193.p.rapidapi.com/";
    private static final String HISTORY_URL = BASE_URL + "history";
    private static final String COUNTRIES_URL = BASE_URL + "countries";
    private static final String HEADER_HOST = "covid-193.p.rapidapi.com";
    private static final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    public API1() {
        super(COUNTRIES_URL, HISTORY_URL, HEADER_HOST, HEADER_KEY);
    }

    public List<DataOutput> getCountryByRegion(String country) throws ParseException, InterruptedException {
        return super.getCountryByRegion(country, true);
    } 
}
