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

    public List<Country> getAllCountriesByContinent(String continent) {
        return null;
    }

    public Country getCountryByName(String name) {
        return null;
    }
}
