package ies.hw.hw1.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.hw.hw1.http.BasicHttpClient;
import ies.hw.hw1.http.Client1;
import ies.hw.hw1.http.Client2;
import ies.hw.hw1.models.Cache;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CovidController {
    
    @Autowired
    private Client1 client1;

    @Autowired
    private Client2 client2;

    @GetMapping("/api1/countries")
    public JSONObject getAllCountries() throws ParseException {
        System.out.println("API 1 - Getting all countries");
        return client1.getAllCountries();
    }

    @GetMapping("/api1/countries/{name}")
    public List<JSONObject> getCountryByName(@PathVariable(value = "name") String name, 
    @RequestParam(required = false) String startDay,
    @RequestParam(required = false) String endDay
    ) throws ParseException {

        if (startDay != null && endDay != null) {
            System.out.println("API 1 - Getting Country for day: " + startDay + " until " + endDay);

            return client1.getCountryByRegionAndDate(name, startDay, endDay);
        }

        System.out.println("API 1 - Getting Country by name: " + name);
        return client1.getCountryByRegion(name);
    }

    @GetMapping("/api1/cache/usage")
    public Cache getCache() {
        System.out.println("API 1 - Returning cache info: ");
        return client1.getCacheInfo(); 
    }

    @GetMapping("/api2/countries")
    public JSONObject getAllCountries2() throws ParseException {
        System.out.println("API 2 - Getting all countries");
        return client2.getAllCountries();
    }

    @GetMapping("/api2/countries/{name}")
    public List<JSONObject> getCountryByName2(@PathVariable(value = "name") String name, 
    @RequestParam(required = false) String startDay, 
    @RequestParam(required = false) String endDay) throws ParseException {

        if (startDay != null && endDay != null) {
            System.out.println("API 2 - Getting Country for day: " + startDay + " until " + endDay);
            return client2.getCountryByRegionAndDate(name, startDay, endDay);
        }

        System.out.println("API 2 - Getting Country by name: " + name);
        return client2.getCountryByRegion(name);
    }

}