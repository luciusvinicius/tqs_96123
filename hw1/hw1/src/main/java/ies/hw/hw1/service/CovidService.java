package ies.hw.hw1.service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ies.hw.hw1.http.BasicHttpClient;
import ies.hw.hw1.models.Country;

@Service
public class CovidService {
    
    public ResponseEntity<String> getAllCountries() {
        return BasicHttpClient.getAllCountries();
    }

    public ResponseEntity<String> getStatsByContinent(String continent) {
        return BasicHttpClient.getCountryByRegion(continent);
    }

    public ResponseEntity<String> getStatsByCountry(String name) {
        return BasicHttpClient.getCountryByRegion(name);
    }

    public ResponseEntity<String> getStatsByCountryAndDate(String name, String date) {
        return BasicHttpClient.getCountryByRegionAndDate(name, date);
    }

    public ResponseEntity<String> getAllContinents() {
        ResponseEntity<String> response = BasicHttpClient.getAllCountries();
        return response;
    }
}
