package ies.hw.hw1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ies.hw.hw1.http.BasicHttpClient;
import ies.hw.hw1.models.Cache;

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

    public Cache getCacheInfo() {
        return BasicHttpClient.getCacheInfo();
    }
}
