package ies.hw.hw1.http;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import ies.hw.hw1.models.DataOutput;

@Service
public class API2 extends BasicAPI {
    
    private static final String BASE_URL = "https://covid-19-statistics.p.rapidapi.com/";
    private static final String REPORTS_URL = BASE_URL + "reports";
    private static final String REGIONS_URL = BASE_URL + "regions";
    private static final String HEADER_HOST = "covid-19-statistics.p.rapidapi.com";
    private static final String HEADER_KEY = "3a17131f7emsh4ac69d9c09df18cp1d85e9jsn01ab2bb0f00b";

    public API2() {
        super(REGIONS_URL, REPORTS_URL, HEADER_HOST, HEADER_KEY);
    }

    public List<DataOutput> getCountryByRegion(String country) throws ParseException, InterruptedException {
        try {
            JSONObject response = super.getCountryByUrl(REPORTS_URL + "?region_name=" + country);
            JSONArray dataJson = (JSONArray) response.get("data"); // empty array
            if (dataJson.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return super.getCountryByJsonObject(response, false);
        }
        catch (IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<DataOutput> filterByDateRange(String country, String startDate, String endDate) throws IOException, InterruptedException, ParseException {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DataOutput> responseGroup = new ArrayList<>();
        
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1))
        {
            JSONObject response = super.getCountryByUrl(REPORTS_URL + "?region_name=" + country + "&date=" + date.toString());
            JSONArray dataJson = (JSONArray) response.get("data"); // empty array
            if (dataJson.isEmpty()) {
                continue;
            }
            DataOutput data = new DataOutput(response);
            responseGroup.add(data);
        }

        return responseGroup;
    }  
}
