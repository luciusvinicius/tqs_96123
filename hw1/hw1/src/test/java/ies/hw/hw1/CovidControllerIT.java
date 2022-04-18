package ies.hw.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import ies.hw.hw1.controller.CovidController;
import ies.hw.hw1.http.API1;
import ies.hw.hw1.http.API2;
import ies.hw.hw1.models.Cache;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CovidControllerIT {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    // @MockBean 
    // private API1 client1;

    // @MockBean
    // private API2 client2;

    // private JSONMethods jsonMeth = new JSONMethods();

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
        System.out.println(countries.size());
        assertThat(countries.size(), is(217));
    }

    @Test
    void getSpecificCountryForAPI1() throws Exception {
        ResponseEntity<List<JSONObject>> response = restTemplate.exchange("/api1/countries/brazil", HttpMethod.GET, null, new ParameterizedTypeReference<List<JSONObject>>() {

        });

        List<JSONObject> country = response.getBody();
        System.out.println("Sussy output:");
        System.out.println(country);
        // System.out.println(countries.size());
        // assertThat(countries.size(), is(217));
    }

} 
