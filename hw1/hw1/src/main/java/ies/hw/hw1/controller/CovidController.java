package ies.hw.hw1.controller;

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
import ies.hw.hw1.models.Cache;
import ies.hw.hw1.service.CovidService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CovidController {
    
    @Autowired
    private BasicHttpClient client;

    @GetMapping("/countries")
    public JSONObject getAllCountries() throws ParseException {
        System.out.println("Getting all countries");
        return client.getAllCountries();
    }

    @GetMapping("/countries/{name}")
    public JSONObject getCountryByName(@PathVariable(value = "name") String name, @RequestParam(required = false) String day) throws ParseException {

        if (day != null) {
            System.out.println("Getting Country for day: " + day);
            return client.getCountryByRegionAndDate(name, day);
        }

        System.out.println("Getting Country by name: " + name);
        return client.getCountryByRegion(name);
    }

    @GetMapping("/cache/usage")
    public Cache getCache() {
        System.out.println("Returning cache info: ");
        return client.getCacheInfo();
    }
}