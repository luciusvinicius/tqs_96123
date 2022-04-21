package ies.hw.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import ies.hw.hw1.models.DataOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CovidControllerIT {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllCountriesForAPI1() throws Exception {
        ResponseEntity<JSONObject> response = restTemplate.exchange("/api1/countries", HttpMethod.GET, null, JSONObject.class);
        List<String> countries = (ArrayList<String>) response.getBody().get("response");
        assertThat(countries.size(), is(233));
    }

    @Test
    void getAllCountriesForAPI2() throws Exception {
        ResponseEntity<JSONObject> response = restTemplate.exchange("/api2/countries", HttpMethod.GET, null, JSONObject.class);
        List<String> countries = (ArrayList<String>) response.getBody().get("data");
        assertThat(countries.size(), is(217));
    }

    @Test
    void getSpecificCountryForAPI1() throws Exception {
        validateSpecificCountry("api1");
    }

    @Test
    void getSpecificCountryForAPI2() throws Exception {
        validateSpecificCountry("api2");
    }

    private void validateSpecificCountry(String api) {
        ResponseEntity<List<DataOutput>> response = restTemplate.exchange("/" + api + "/countries/brazil", HttpMethod.GET, null, new ParameterizedTypeReference<List<DataOutput>>() {

        });
        
        List<DataOutput> countries = response.getBody();
        assertThat(countries.size(), is(1));
        assertThat(countries.get(0).getCountry(), is("Brazil"));
    }

    @Test
    void getSpecificCountryAndDateRangeForAPI1() throws Exception {
        validateDateTest("api1");
        
    }

    @Test
    void getSpecificCountryAndDateRangeForAPI2() throws Exception {
        validateDateTest("api2");
    }

    private void validateDateTest(String api) {
        ResponseEntity<List<DataOutput>> response = restTemplate.exchange("/" + api + "/countries/brazil?startDay=2021-09-30&endDay=2021-10-02", HttpMethod.GET, null, new ParameterizedTypeReference<List<DataOutput>>() {

        });

        List<DataOutput> countries = response.getBody();
        assertThat(countries.size(), is(3));
        assertThat(countries.get(0).getCountry(), is("Brazil"));
        assertThat(countries.get(0).getDate(), is(LocalDate.parse("2021-09-30")));
        assertThat(countries.get(1).getCountry(), is("Brazil"));
        assertThat(countries.get(1).getDate(), is(LocalDate.parse("2021-10-01")));
        assertThat(countries.get(2).getCountry(), is("Brazil"));
        assertThat(countries.get(2).getDate(), is(LocalDate.parse("2021-10-02")));

    }

    @Test
    void getSpecificCountryNotFoundForAPI1() {
        validateCountryNotFound("api1");
    }

    @Test
    void getSpecificCountryNotFoundForAPI2() {
        validateCountryNotFound("api2");
    }

    private void validateCountryNotFound(String api) {
        ResponseEntity<List<DataOutput>> response = restTemplate.exchange("/" + api + "/countries/not_existent", HttpMethod.GET, null, new ParameterizedTypeReference<List<DataOutput>>() {

        });
        List<DataOutput> countries = response.getBody();

        assertThat(countries, hasSize(0));

    }
} 
