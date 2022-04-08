package ies.hw.hw1.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.hw.hw1.models.Country;
import ies.hw.hw1.service.CovidService;

@RestController
public class CovidController {
    
    @Autowired
    private CovidService service;

    @GetMapping("/countries")
    public ResponseEntity<String> getAllCountries(@RequestParam(required = false) String continent) {
        if (continent != null) {
            System.out.println("Getting all countries for continent: " + continent);
            return service.getAllCountries();
        }
        System.out.println("Getting all countries");
        return service.getAllCountries();
    }

    @GetMapping("/countries/{name}")
    public ResponseEntity<String> getCountryByName(@PathVariable(value = "name") String name) {
        System.out.println("Getting Country by name: " + name);
        return service.getStatsByCountry(name);
    }

    // TODO: GetMapping to continents?????

    @GetMapping("/continents/{name}")
    public ResponseEntity<String> getContinents(@PathVariable(value = "name") String name) {
        System.out.println("Getting Continent by name: " + name);
        return service.getStatsByContinent(name);
    }



}
