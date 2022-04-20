package ies.hw.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ies.hw.hw1.controller.CovidController;
import ies.hw.hw1.http.API1;
import ies.hw.hw1.http.API2;
import ies.hw.hw1.http.Client;
import ies.hw.hw1.http.WeakConcurrentHashMap;
import ies.hw.hw1.models.Cache;
import ies.hw.hw1.models.DataOutput;

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.empty;


import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class API1Test {

    @Mock
    Client client;

    private API1 api = new API1();

    private final String BASE_URL = "https://covid-193.p.rapidapi.com/";
    private final String STATISTICS_URL = BASE_URL + "statistics";
    private final String HISTORY_URL = BASE_URL + "history";
    private final String COUNTRIES_URL = BASE_URL + "countries";

    private final String ALL_COUNTRIES = "{\"get\":\"countries\", \"parameters\":[], \"errors\":[], \"results\":233, \"response\":[\"Afghanistan\",\"Albania\",\"Algeria\",\"Andorra\",\"Angola\",\"Anguilla\",\"Antigua-and-Barbuda\",\"Argentina\",\"Armenia\",\"Aruba\",\"Australia\",\"Austria\",\"Azerbaijan\",\"Bahamas\",\"Bahrain\",\"Bangladesh\",\"Barbados\",\"Belarus\",\"Belgium\",\"Belize\",\"Benin\",\"Bermuda\",\"Bhutan\"]}";
    private final String SPECIFIC_COUNTRY = "{\"get\":\"history\",\"parameters\":{\"country\":\"brazil\",\"day\":\"" + LocalDate.now().toString() + "\"},\"errors\":[],\"results\":2,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215266331,\"cases\":{\"new\":\"+8470\",\"active\":336529,\"critical\":8318,\"recovered\":29262483,\"1M_pop\":\"140575\",\"total\":30261088},\"deaths\":{\"new\":\"+65\",\"1M_pop\":\"3076\",\"total\":662076},\"tests\":{\"1M_pop\":\"296266\",\"total\":63776166},\"day\":\"" + LocalDate.now().toString() + "\",\"time\":\"" + LocalDate.now().toString() + "T20:45:02+00:00\"},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215262209,\"cases\":{\"new\":\"+8470\",\"active\":336529,\"critical\":8318,\"recovered\":29262483,\"1M_pop\":\"140578\",\"total\":30261088},\"deaths\":{\"new\":\"+65\",\"1M_pop\":\"3076\",\"total\":662076},\"tests\":{\"1M_pop\":\"296272\",\"total\":63776166},\"day\":\"" + LocalDate.now().toString() + "\",\"time\":\"" + LocalDate.now().toString() + "T00:00:03+00:00\"}]}";
    private final String SPECIFIC_COUNTRY_WITH_START_DATE = "{\"get\":\"history\",\"parameters\":{\"country\":\"brazil\",\"day\":\"2021-09-01\"},\"errors\":[],\"results\":4,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214318271,\"cases\":{\"new\":\"+26348\",\"active\":447192,\"critical\":8318,\"recovered\":19775873,\"1M_pop\":\"97072\",\"total\":20804215},\"deaths\":{\"new\":\"+625\",\"1M_pop\":\"2712\",\"total\":581150},\"tests\":{\"1M_pop\":\"265480\",\"total\":56897224},\"day\":\"2021-09-01\",\"time\":\"2021-09-01T23:45:02+00:00\"},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214318271,\"cases\":{\"new\":\"+26348\",\"active\":487618,\"critical\":8318,\"recovered\":19735447,\"1M_pop\":\"97072\",\"total\":20804215},\"deaths\":{\"new\":\"+625\",\"1M_pop\":\"2712\",\"total\":581150},\"tests\":{\"1M_pop\":\"265480\",\"total\":56897224},\"day\":\"2021-09-01\",\"time\":\"2021-09-01T22:00:02+00:00\"},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214318271,\"cases\":{\"new\":\"+25586\",\"active\":461895,\"critical\":8318,\"recovered\":19735447,\"1M_pop\":\"96949\",\"total\":20777867},\"deaths\":{\"new\":\"+882\",\"1M_pop\":\"2709\",\"total\":580525},\"tests\":{\"1M_pop\":\"265480\",\"total\":56897224},\"day\":\"2021-09-01\",\"time\":\"2021-09-01T21:45:03+00:00\"},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214314149,\"cases\":{\"new\":\"+25586\",\"active\":461895,\"critical\":8318,\"recovered\":19735447,\"1M_pop\":\"96951\",\"total\":20777867},\"deaths\":{\"new\":\"+882\",\"1M_pop\":\"2709\",\"total\":580525},\"tests\":{\"1M_pop\":\"265485\",\"total\":56897224},\"day\":\"2021-09-01\",\"time\":\"2021-09-01T00:00:02+00:00\"}]}";
    private final String SPECIFIC_COUNTRY_WITH_END_DATE = "{\"get\":\"history\",\"parameters\":{\"country\":\"brazil\",\"day\":\"2021-09-02\"},\"errors\":[],\"results\":3,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214322393,\"cases\":{\"new\":\"+26280\",\"active\":472708,\"critical\":8318,\"recovered\":19775873,\"1M_pop\":\"97192\",\"total\":20830495},\"deaths\":{\"new\":\"+686\",\"1M_pop\":\"2715\",\"total\":581914},\"tests\":{\"1M_pop\":\"265475\",\"total\":56897224},\"day\":\"2021-09-02\",\"time\":\"2021-09-02T23:00:02+00:00\"},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214322393,\"cases\":{\"new\":\"+26348\",\"active\":447114,\"critical\":8318,\"recovered\":19775873,\"1M_pop\":\"97070\",\"total\":20804215},\"deaths\":{\"new\":\"+703\",\"1M_pop\":\"2712\",\"total\":581228},\"tests\":{\"1M_pop\":\"265475\",\"total\":56897224},\"day\":\"2021-09-02\",\"time\":\"2021-09-02T21:30:03+00:00\"},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":214318271,\"cases\":{\"new\":\"+26348\",\"active\":447114,\"critical\":8318,\"recovered\":19775873,\"1M_pop\":\"97072\",\"total\":20804215},\"deaths\":{\"new\":\"+703\",\"1M_pop\":\"2712\",\"total\":581228},\"tests\":{\"1M_pop\":\"265480\",\"total\":56897224},\"day\":\"2021-09-02\",\"time\":\"2021-09-02T00:00:02+00:00\"}]}";

    private WeakConcurrentHashMap<String, JSONObject> cache;
    private final String NOT_FOUND = "{\"get\":\"history\",\"parameters\":{\"country\":\"not_existent\"},\"errors\":[],\"results\":0,\"response\":[]}";

    private JSONMethods jsonMeth = new JSONMethods();

    @BeforeEach
    void setUp() throws IOException, InterruptedException, ParseException {
        client = mock(Client.class);
        
        api.setClient(client);
        cache = api.getConcurrentCash();
    }

    @Test
    void getAllCountries() throws IOException, InterruptedException, ParseException {
        
        when(client.doRequest(COUNTRIES_URL, cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(ALL_COUNTRIES));

        JSONObject response = api.getAllCountries();
        JSONArray data = (JSONArray) response.get("response");

        assertThat(data.size(), is(23));
        assertThat(data.get(0), is("Afghanistan"));
    }

    @Test
    void getSpecificCountry() throws IOException, InterruptedException, ParseException {
        when(client.doRequest(HISTORY_URL + "?country=brazil&day=" + LocalDate.now().toString(), cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(SPECIFIC_COUNTRY));

        DataOutput data = api.getCountryByRegion("brazil").get(0);
       
        assertThat(data.getCountry(), is("Brazil"));
        System.out.println(SPECIFIC_COUNTRY);
        assertThat(data.getDate(), is(LocalDate.now()));
    }

    @Test
    void getSpecificCountryWithDate() throws IOException, InterruptedException, ParseException {
        when(client.doRequest(HISTORY_URL + "?country=brazil&day=2021-09-01", cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(SPECIFIC_COUNTRY_WITH_START_DATE));

        when(client.doRequest(HISTORY_URL + "?country=brazil&day=2021-09-02", cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(SPECIFIC_COUNTRY_WITH_END_DATE));

        List<DataOutput> response = api.getCountryByRegionAndDate("brazil","2021-09-01","2021-09-02");

        assertThat(response.get(0).getCountry(), is("Brazil"));
        assertThat(response.get(1).getCountry(), is("Brazil"));
        assertThat(response.get(0).getDate(), is(LocalDate.parse("2021-09-01")));
        assertThat(response.get(1).getDate(), is(LocalDate.parse("2021-09-02")));

    }

    @Test
    void getSpecificCountryNotFound() throws IOException, InterruptedException, ParseException {
        when(client.doRequest(HISTORY_URL + "?country=not_existent&day=" + LocalDate.now().toString(), cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(NOT_FOUND));

        List<DataOutput> response = api.getCountryByRegion("not_existent");
       
        assertTrue(response.isEmpty());
    }

}
