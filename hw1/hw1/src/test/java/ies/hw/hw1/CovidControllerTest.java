package ies.hw.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ies.hw.hw1.controller.CovidController;
import ies.hw.hw1.http.API1;
import ies.hw.hw1.http.API2;
import ies.hw.hw1.models.Cache;
import ies.hw.hw1.models.DataOutput;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
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

@WebMvcTest(CovidController.class)
public class CovidControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean 
    private API1 client1;

    @MockBean
    private API2 client2;

    private JSONMethods jsonMeth = new JSONMethods();

    @Test
    void getAllCountriesForAPI1() throws Exception {

        JSONObject all_countries = jsonMeth.generateJSONObject("{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Afghanistan\",\"Albania\",\"Algeria\",\"Andorra\",\"Angola\",\"Anguilla\",\"Antigua-and-Barbuda\",\"Argentina\",\"Armenia\",\"Aruba\",\"Australia\",\"Austria\",\"Azerbaijan\",\"Bahamas\",\"Bahrain\",\"Bangladesh\",\"Barbados\",\"Belarus\",\"Belgium\",\"Belize\",\"Benin\",\"Bermuda\",\"Bhutan\",\"Bolivia\",\"Bosnia-and-Herzegovina\",\"Botswana\",\"Brazil\",\"British-Virgin-Islands\",\"Brunei\",\"Bulgaria\",\"Burkina-Faso\",\"Burundi\",\"Cabo-Verde\",\"Cambodia\",\"Cameroon\",\"Canada\",\"CAR\",\"Caribbean-Netherlands\",\"Cayman-Islands\",\"Chad\",\"Channel-Islands\",\"Chile\",\"China\",\"Colombia\",\"Comoros\",\"Congo\",\"Cook-Islands\",\"Costa-Rica\",\"Croatia\",\"Cuba\",\"Cura&ccedil;ao\",\"Cyprus\",\"Czechia\",\"Denmark\",\"Diamond-Princess\",\"Diamond-Princess-\",\"Djibouti\",\"Dominica\",\"Dominican-Republic\",\"DRC\",\"Ecuador\",\"Egypt\",\"El-Salvador\",\"Equatorial-Guinea\",\"Eritrea\",\"Estonia\",\"Eswatini\",\"Ethiopia\",\"Faeroe-Islands\",\"Falkland-Islands\",\"Fiji\",\"Finland\",\"France\",\"French-Guiana\",\"French-Polynesia\",\"Gabon\",\"Gambia\",\"Georgia\",\"Germany\",\"Ghana\",\"Gibraltar\",\"Greece\",\"Greenland\",\"Grenada\",\"Guadeloupe\",\"Guam\",\"Guatemala\",\"Guinea\",\"Guinea-Bissau\",\"Guyana\",\"Haiti\",\"Honduras\",\"Hong-Kong\",\"Hungary\",\"Iceland\",\"India\",\"Indonesia\",\"Iran\",\"Iraq\",\"Ireland\",\"Isle-of-Man\",\"Israel\",\"Italy\",\"Ivory-Coast\",\"Jamaica\",\"Japan\",\"Jordan\",\"Kazakhstan\",\"Kenya\",\"Kiribati\",\"Kuwait\",\"Kyrgyzstan\",\"Laos\",\"Latvia\",\"Lebanon\",\"Lesotho\",\"Liberia\",\"Libya\",\"Liechtenstein\",\"Lithuania\",\"Luxembourg\",\"Macao\",\"Madagascar\",\"Malawi\",\"Malaysia\",\"Maldives\",\"Mali\",\"Malta\",\"Marshall-Islands\",\"Martinique\",\"Mauritania\",\"Mauritius\",\"Mayotte\",\"Mexico\",\"Micronesia\",\"Moldova\",\"Monaco\",\"Mongolia\",\"Montenegro\",\"Montserrat\",\"Morocco\",\"Mozambique\",\"MS-Zaandam\",\"MS-Zaandam-\",\"Myanmar\",\"Namibia\",\"Nauru\",\"Nepal\",\"Netherlands\",\"New-Caledonia\",\"New-Zealand\",\"Nicaragua\",\"Niger\",\"Nigeria\",\"Niue\",\"North-Macedonia\",\"Norway\",\"Oman\",\"Pakistan\",\"Palau\",\"Palestine\",\"Panama\",\"Papua-New-Guinea\",\"Paraguay\",\"Peru\",\"Philippines\",\"Poland\",\"Portugal\",\"Puerto-Rico\",\"Qatar\",\"R&eacute;union\",\"Romania\",\"Russia\",\"Rwanda\",\"S-Korea\",\"Saint-Helena\",\"Saint-Kitts-and-Nevis\",\"Saint-Lucia\",\"Saint-Martin\",\"Saint-Pierre-Miquelon\",\"Samoa\",\"San-Marino\",\"Sao-Tome-and-Principe\",\"Saudi-Arabia\",\"Senegal\",\"Serbia\",\"Seychelles\",\"Sierra-Leone\",\"Singapore\",\"Sint-Maarten\",\"Slovakia\",\"Slovenia\",\"Solomon-Islands\",\"Somalia\",\"South-Africa\",\"South-Sudan\",\"Spain\",\"Sri-Lanka\",\"St-Barth\",\"St-Vincent-Grenadines\",\"Sudan\",\"Suriname\",\"Sweden\",\"Switzerland\",\"Syria\",\"Taiwan\",\"Tajikistan\",\"Tanzania\",\"Thailand\",\"Timor-Leste\",\"Togo\",\"Tonga\",\"Trinidad-and-Tobago\",\"Tunisia\",\"Turkey\",\"Turks-and-Caicos\",\"UAE\",\"Uganda\",\"UK\",\"Ukraine\",\"Uruguay\",\"US-Virgin-Islands\",\"USA\",\"Uzbekistan\",\"Vanuatu\",\"Vatican-City\",\"Venezuela\",\"Vietnam\",\"Wallis-and-Futuna\",\"Western-Sahara\",\"Yemen\",\"Zambia\",\"Zimbabwe\"]}");

        when(client1.getAllCountries()).thenReturn(all_countries);

        mvc.perform(
            get("/api1/countries")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", is(233)));

        verify(client1, times(1)).getAllCountries();
    }

    @Test
    void getAllCountriesForAPI2() throws Exception {

        JSONObject all_countries = jsonMeth.generateJSONObject("{\"data\":[{\"iso\":\"CHN\",\"name\":\"China\"},{\"iso\":\"TWN\",\"name\":\"Taipei and environs\"},{\"iso\":\"USA\",\"name\":\"US\"},{\"iso\":\"JPN\",\"name\":\"Japan\"},{\"iso\":\"THA\",\"name\":\"Thailand\"},{\"iso\":\"KOR\",\"name\":\"Korea, South\"},{\"iso\":\"SGP\",\"name\":\"Singapore\"},{\"iso\":\"PHL\",\"name\":\"Philippines\"},{\"iso\":\"MYS\",\"name\":\"Malaysia\"},{\"iso\":\"VNM\",\"name\":\"Vietnam\"},{\"iso\":\"AUS\",\"name\":\"Australia\"},{\"iso\":\"MEX\",\"name\":\"Mexico\"},{\"iso\":\"BRA\",\"name\":\"Brazil\"},{\"iso\":\"COL\",\"name\":\"Colombia\"}]}");

        when(client2.getAllCountries()).thenReturn(all_countries);

        mvc.perform(
            get("/api2/countries")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", hasSize(14)));

        verify(client2, times(1)).getAllCountries();
    }

    @Test
    void getSpecificCountryForAPI1() throws Exception {

        DataOutput specific_country = new DataOutput();
        specific_country.setCountry("Brazil");
        specific_country.setDate(LocalDate.parse("2022-04-18"));
        specific_country.setActive(363556L);
        specific_country.setDeaths(662011L);
        specific_country.setNewActive(2541L);
        specific_country.setNewDeaths(18L);
        specific_country.setRecovered(29227051L);

        String country = "brazil";

        List<DataOutput> lst = new ArrayList<>();
        lst.add(specific_country);

        when(client1.getCountryByRegion(country)).thenReturn(lst);

        mvc.perform(
            get("/api1/countries/brazil")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].country", is("Brazil")))
        .andExpect(jsonPath("$[0].date", is("2022-04-18")));

        verify(client1, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryForAPI2() throws Exception {

        DataOutput specific_country = new DataOutput();
        specific_country.setCountry("Brazil");
        specific_country.setDate(LocalDate.parse("2022-04-18"));
        specific_country.setActive(363556L);
        specific_country.setDeaths(662011L);
        specific_country.setNewActive(2541L);
        specific_country.setNewDeaths(18L);
        specific_country.setRecovered(29227051L);

        String country = "brazil";

        List<DataOutput> lst = new ArrayList<>();
        lst.add(specific_country);

        when(client2.getCountryByRegion(country)).thenReturn(lst);

        mvc.perform(
            get("/api2/countries/brazil")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].country", is("Brazil")))
        .andExpect(jsonPath("$[0].date", is("2022-04-18")));

        verify(client2, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryAndDateRangeForAPI1() throws Exception {

        String startDate = "2021-09-30";
        String endDate = "2021-10-01";

        DataOutput specific_country = new DataOutput();
        specific_country.setCountry("Brazil");
        specific_country.setDate(LocalDate.parse(startDate));
        specific_country.setActive(425623L);
        specific_country.setDeaths(596749L);
        specific_country.setNewActive(27527L);
        specific_country.setNewDeaths(586L);
        specific_country.setRecovered(20404701L);

        DataOutput specific_country2 = new DataOutput();
        specific_country2.setCountry("Brazil");
        specific_country2.setDate(LocalDate.parse(endDate));
        specific_country2.setActive(423257L);
        specific_country2.setDeaths(597255L);
        specific_country2.setNewActive(18578L);
        specific_country2.setNewDeaths(455L);
        specific_country2.setRecovered(20425139L);

        String country = "brazil";

        List<DataOutput> lst = new ArrayList<>();
        lst.add(specific_country);
        lst.add(specific_country2);

        when(client1.getCountryByRegionAndDate(country, startDate, endDate)).thenReturn(lst);

        mvc.perform(
            get("/api1/countries/brazil?startDay=2021-09-30&endDay=2021-10-01")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].country", is("Brazil")))
        .andExpect(jsonPath("$[0].date", is("2021-09-30")))
        .andExpect(jsonPath("$[1].country", is("Brazil")))
        .andExpect(jsonPath("$[1].date", is("2021-10-01")));

        verify(client1, times(1)).getCountryByRegionAndDate(country, startDate, endDate);
    }

    @Test
    void getSpecificCountryAndDateRangeForAPI2() throws Exception {

        String startDate = "2021-09-30";
        String endDate = "2021-10-01";

        DataOutput specific_country = new DataOutput();
        specific_country.setCountry("Brazil");
        specific_country.setDate(LocalDate.parse(startDate));
        specific_country.setActive(425623L);
        specific_country.setDeaths(596749L);
        specific_country.setNewActive(27527L);
        specific_country.setNewDeaths(586L);
        specific_country.setRecovered(20404701L);

        DataOutput specific_country2 = new DataOutput();
        specific_country2.setCountry("Brazil");
        specific_country2.setDate(LocalDate.parse(endDate));
        specific_country2.setActive(423257L);
        specific_country2.setDeaths(597255L);
        specific_country2.setNewActive(18578L);
        specific_country2.setNewDeaths(455L);
        specific_country2.setRecovered(20425139L);

        String country = "brazil";

        List<DataOutput> lst = new ArrayList<>();
        lst.add(specific_country);
        lst.add(specific_country2);

        when(client2.getCountryByRegionAndDate(country, startDate, endDate)).thenReturn(lst);

        mvc.perform(
            get("/api2/countries/brazil?startDay=2021-09-30&endDay=2021-10-01")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].country", is("Brazil")))
        .andExpect(jsonPath("$[0].date", is("2021-09-30")))
        .andExpect(jsonPath("$[1].country", is("Brazil")))
        .andExpect(jsonPath("$[1].date", is("2021-10-01")));

        verify(client2, times(1)).getCountryByRegionAndDate(country, startDate, endDate);
    }


    @Test
    void getSpecificCountryNotFoundForAPI1() throws Exception {

        String country = "not_existent";

        when(client1.getCountryByRegion(country)).thenReturn(new ArrayList<>());

        mvc.perform(
            get("/api1/countries/not_existent")
        )
        .andExpect(jsonPath("$", hasSize(0)));

        verify(client1, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryNotFoundForAPI2() throws Exception {

        String country = "not_existent";

        when(client2.getCountryByRegion(country)).thenReturn(new ArrayList<>());

        mvc.perform(
            get("/api2/countries/not_existent")
        )
        .andExpect(jsonPath("$", hasSize(0)));

        verify(client2, times(1)).getCountryByRegion(country);
    }

    @Test
    void getCacheUsageForAPI1() throws Exception {

        Cache cache_usage = new Cache(2, 1, 1000000);

        when(client1.getCacheInfo()).thenReturn(cache_usage);

        mvc.perform(
            get("/api1/cache/usage")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.numberOfHits", is(2)))
        .andExpect(jsonPath("$.numberOfMisses", is(1)))
        .andExpect(jsonPath("$.numberOfRequests", is(3)));
        // "{\"numberOfHits\":2,\"numberOfMisses\":1,\"ttl\":1000000,\"numberOfRequests\":3}",

        verify(client1, times(1)).getCacheInfo();
    }

    @Test
    void getCacheUsageForAPI2() throws Exception {

        Cache cache_usage = new Cache(2, 1, 1000000);

        when(client2.getCacheInfo()).thenReturn(cache_usage);

        mvc.perform(
            get("/api2/cache/usage")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.numberOfHits", is(2)))
        .andExpect(jsonPath("$.numberOfMisses", is(1)))
        .andExpect(jsonPath("$.numberOfRequests", is(3)));
        // "{\"numberOfHits\":2,\"numberOfMisses\":1,\"ttl\":1000000,\"numberOfRequests\":3}",

        verify(client2, times(1)).getCacheInfo();
    }
}
