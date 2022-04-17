package ies.hw.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import ies.hw.hw1.controller.CovidController;
import ies.hw.hw1.http.BasicHttpClient;
import ies.hw.hw1.http.Client1;
import ies.hw.hw1.http.Client2;
// import ies.hw.hw1.service.CovidService;
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

@WebMvcTest(CovidController.class)
public class CovidControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean 
    private Client1 client1;

    @MockBean
    private Client2 client2;

    private JSONArray generateJSONArray(String s) throws ParseException {
        // System.out.println("Sussy object: " + s);
        return (JSONArray) new JSONParser().parse(s);
    }

    private JSONObject generateJSONObject(String s) throws ParseException {
        // System.out.println("Sussy object: " + s);
        return (JSONObject) new JSONParser().parse(s);
    }

    private List<JSONObject> jsonArrayToListOfObjects(JSONArray array) {
        List<JSONObject> retJson = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            retJson.add((JSONObject) array.get(i));
        }

        return retJson;
    }

    @Test
    void getAllCountriesForAPI1() throws Exception {

        JSONObject all_countries = generateJSONObject("{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Afghanistan\",\"Albania\",\"Algeria\",\"Andorra\",\"Angola\",\"Anguilla\",\"Antigua-and-Barbuda\",\"Argentina\",\"Armenia\",\"Aruba\",\"Australia\",\"Austria\",\"Azerbaijan\",\"Bahamas\",\"Bahrain\",\"Bangladesh\",\"Barbados\",\"Belarus\",\"Belgium\",\"Belize\",\"Benin\",\"Bermuda\",\"Bhutan\",\"Bolivia\",\"Bosnia-and-Herzegovina\",\"Botswana\",\"Brazil\",\"British-Virgin-Islands\",\"Brunei\",\"Bulgaria\",\"Burkina-Faso\",\"Burundi\",\"Cabo-Verde\",\"Cambodia\",\"Cameroon\",\"Canada\",\"CAR\",\"Caribbean-Netherlands\",\"Cayman-Islands\",\"Chad\",\"Channel-Islands\",\"Chile\",\"China\",\"Colombia\",\"Comoros\",\"Congo\",\"Cook-Islands\",\"Costa-Rica\",\"Croatia\",\"Cuba\",\"Cura&ccedil;ao\",\"Cyprus\",\"Czechia\",\"Denmark\",\"Diamond-Princess\",\"Diamond-Princess-\",\"Djibouti\",\"Dominica\",\"Dominican-Republic\",\"DRC\",\"Ecuador\",\"Egypt\",\"El-Salvador\",\"Equatorial-Guinea\",\"Eritrea\",\"Estonia\",\"Eswatini\",\"Ethiopia\",\"Faeroe-Islands\",\"Falkland-Islands\",\"Fiji\",\"Finland\",\"France\",\"French-Guiana\",\"French-Polynesia\",\"Gabon\",\"Gambia\",\"Georgia\",\"Germany\",\"Ghana\",\"Gibraltar\",\"Greece\",\"Greenland\",\"Grenada\",\"Guadeloupe\",\"Guam\",\"Guatemala\",\"Guinea\",\"Guinea-Bissau\",\"Guyana\",\"Haiti\",\"Honduras\",\"Hong-Kong\",\"Hungary\",\"Iceland\",\"India\",\"Indonesia\",\"Iran\",\"Iraq\",\"Ireland\",\"Isle-of-Man\",\"Israel\",\"Italy\",\"Ivory-Coast\",\"Jamaica\",\"Japan\",\"Jordan\",\"Kazakhstan\",\"Kenya\",\"Kiribati\",\"Kuwait\",\"Kyrgyzstan\",\"Laos\",\"Latvia\",\"Lebanon\",\"Lesotho\",\"Liberia\",\"Libya\",\"Liechtenstein\",\"Lithuania\",\"Luxembourg\",\"Macao\",\"Madagascar\",\"Malawi\",\"Malaysia\",\"Maldives\",\"Mali\",\"Malta\",\"Marshall-Islands\",\"Martinique\",\"Mauritania\",\"Mauritius\",\"Mayotte\",\"Mexico\",\"Micronesia\",\"Moldova\",\"Monaco\",\"Mongolia\",\"Montenegro\",\"Montserrat\",\"Morocco\",\"Mozambique\",\"MS-Zaandam\",\"MS-Zaandam-\",\"Myanmar\",\"Namibia\",\"Nauru\",\"Nepal\",\"Netherlands\",\"New-Caledonia\",\"New-Zealand\",\"Nicaragua\",\"Niger\",\"Nigeria\",\"Niue\",\"North-Macedonia\",\"Norway\",\"Oman\",\"Pakistan\",\"Palau\",\"Palestine\",\"Panama\",\"Papua-New-Guinea\",\"Paraguay\",\"Peru\",\"Philippines\",\"Poland\",\"Portugal\",\"Puerto-Rico\",\"Qatar\",\"R&eacute;union\",\"Romania\",\"Russia\",\"Rwanda\",\"S-Korea\",\"Saint-Helena\",\"Saint-Kitts-and-Nevis\",\"Saint-Lucia\",\"Saint-Martin\",\"Saint-Pierre-Miquelon\",\"Samoa\",\"San-Marino\",\"Sao-Tome-and-Principe\",\"Saudi-Arabia\",\"Senegal\",\"Serbia\",\"Seychelles\",\"Sierra-Leone\",\"Singapore\",\"Sint-Maarten\",\"Slovakia\",\"Slovenia\",\"Solomon-Islands\",\"Somalia\",\"South-Africa\",\"South-Sudan\",\"Spain\",\"Sri-Lanka\",\"St-Barth\",\"St-Vincent-Grenadines\",\"Sudan\",\"Suriname\",\"Sweden\",\"Switzerland\",\"Syria\",\"Taiwan\",\"Tajikistan\",\"Tanzania\",\"Thailand\",\"Timor-Leste\",\"Togo\",\"Tonga\",\"Trinidad-and-Tobago\",\"Tunisia\",\"Turkey\",\"Turks-and-Caicos\",\"UAE\",\"Uganda\",\"UK\",\"Ukraine\",\"Uruguay\",\"US-Virgin-Islands\",\"USA\",\"Uzbekistan\",\"Vanuatu\",\"Vatican-City\",\"Venezuela\",\"Vietnam\",\"Wallis-and-Futuna\",\"Western-Sahara\",\"Yemen\",\"Zambia\",\"Zimbabwe\"]}");

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

        JSONObject all_countries = generateJSONObject("{\"data\":[{\"iso\":\"CHN\",\"name\":\"China\"},{\"iso\":\"TWN\",\"name\":\"Taipei and environs\"},{\"iso\":\"USA\",\"name\":\"US\"},{\"iso\":\"JPN\",\"name\":\"Japan\"},{\"iso\":\"THA\",\"name\":\"Thailand\"},{\"iso\":\"KOR\",\"name\":\"Korea, South\"},{\"iso\":\"SGP\",\"name\":\"Singapore\"},{\"iso\":\"PHL\",\"name\":\"Philippines\"},{\"iso\":\"MYS\",\"name\":\"Malaysia\"},{\"iso\":\"VNM\",\"name\":\"Vietnam\"},{\"iso\":\"AUS\",\"name\":\"Australia\"},{\"iso\":\"MEX\",\"name\":\"Mexico\"},{\"iso\":\"BRA\",\"name\":\"Brazil\"},{\"iso\":\"COL\",\"name\":\"Colombia\"}]}");

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

        JSONArray specific_country = generateJSONArray("[{\"get\":\"history\",\"parameters\":{\"country\":\"brazil\"},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215245721,\"cases\":{\"new\":\"+26924\",\"active\":381625,\"critical\":8318,\"recovered\":29167518,\"1M_pop\":\"140355\",\"total\":30210853},\"deaths\":{\"new\":\"+158\",\"1M_pop\":\"3074\",\"total\":661710},\"tests\":{\"1M_pop\":\"296295\",\"total\":63776166},\"day\":\"2022-04-14\",\"time\":\"2022-04-14T15:15:03+00:00\"}]}]");
        String country = "brazil";
        // List<JSONObject> group = new ArrayList<>();
        // group.add(specific_country);

        when(client1.getCountryByRegion(country)).thenReturn(jsonArrayToListOfObjects(specific_country));

        mvc.perform(
            get("/api1/countries/brazil")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].results", is(1)))
        .andExpect(jsonPath("$[0].response[0].country", is("Brazil")));

        verify(client1, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryForAPI2() throws Exception {

        JSONArray specific_country = generateJSONArray("[{\"data\":[{\"date\":\"2022-04-16\",\"confirmed_diff\":0,\"active_diff\":0,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0161,\"last_update\":\"2022-04-17 04:20:59\",\"active\":122358,\"region\":{\"iso\":\"BRA\",\"province\":\"Acre\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.0238\",\"long\":\"-70.812\"},\"confirmed\":124354,\"deaths\":1996}]}]");
        String country = "brazil";
        // List<JSONObject> group = new ArrayList<>();
        // group.add(specific_country);

        when(client1.getCountryByRegion(country)).thenReturn(jsonArrayToListOfObjects(specific_country));

        mvc.perform(
            get("/api1/countries/brazil")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].data[0].fatality_rate", is(0.0161)))
        .andExpect(jsonPath("$[0].data[0].region.name", is("Brazil")));

        verify(client1, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryNotFoundForAPI1() throws Exception {

        JSONObject country_not_found = generateJSONObject("{\"get\":\"history\",\"parameters\":{\"country\":\"not_existent\"},\"errors\":[],\"results\":0,\"response\":[]}");
        String country = "not_existent";
        List<JSONObject> group = new ArrayList<>();
        group.add(country_not_found);

        when(client1.getCountryByRegion(country)).thenReturn(group);

        mvc.perform(
            get("/api1/countries/not_existent")
        )
        .andExpect(jsonPath("$[0].results", is(0)));

        verify(client1, times(1)).getCountryByRegion(country);
    }

    // @Test
    // void getSpecificCountryNotFoundForAPI2() throws Exception {

    //     JSONObject country_not_found = generateJSONObject("[{\"data\":[]}]");
    //     String country = "not_existent";
    //     List<JSONObject> group = new ArrayList<>();
    //     group.add(country_not_found);

    //     when(client1.getCountryByRegion(country)).thenReturn(group);

    //     mvc.perform(
    //         get("/api2/countries/not_existent")
    //     )
    //     .andExpect(jsonPath("$[0].data", is(empty())));

    //     verify(client1, times(1)).getCountryByRegion(country);
    // }

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
}
