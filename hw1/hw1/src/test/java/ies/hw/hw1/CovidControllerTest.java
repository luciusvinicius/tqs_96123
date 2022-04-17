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

        when(client2.getCountryByRegion(country)).thenReturn(jsonArrayToListOfObjects(specific_country));

        mvc.perform(
            get("/api2/countries/brazil")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].data[0].fatality_rate", is(0.0161)))
        .andExpect(jsonPath("$[0].data[0].region.name", is("Brazil")));

        verify(client2, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryAndDateRangeForAPI1() throws Exception {

        String startDay = "2021-09-30";
        String endDay = "2021-10-01";

        JSONArray specific_country = generateJSONArray("[{\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"cases\":{\"new\":\"+27527\",\"recovered\":20404701,\"total\":21427073,\"critical\":8318,\"active\":425623,\"1M_pop\":\"99922\"},\"tests\":{\"total\":57282520,\"1M_pop\":\"267129\"},\"time\":\"2021-09-30T23:15:03+00:00\",\"day\":\"2021-09-30\",\"deaths\":{\"new\":\"+586\",\"total\":596749,\"1M_pop\":\"2783\"},\"population\":214437809},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"cases\":{\"new\":\"+17756\",\"recovered\":20404701,\"total\":21399546,\"critical\":8318,\"active\":398682,\"1M_pop\":\"99794\"},\"tests\":{\"total\":57282520,\"1M_pop\":\"267129\"},\"time\":\"2021-09-30T21:00:02+00:00\",\"day\":\"2021-09-30\",\"deaths\":{\"new\":\"+643\",\"total\":596163,\"1M_pop\":\"2780\"},\"population\":214437809},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"cases\":{\"new\":\"+17756\",\"recovered\":20404701,\"total\":21399546,\"critical\":8318,\"active\":398682,\"1M_pop\":\"99796\"},\"tests\":{\"total\":57282520,\"1M_pop\":\"267134\"},\"time\":\"2021-09-30T00:00:03+00:00\",\"day\":\"2021-09-30\",\"deaths\":{\"new\":\"+643\",\"total\":596163,\"1M_pop\":\"2780\"},\"population\":214433687}],\"get\":\"history\",\"parameters\":{\"country\":\"brazil\",\"day\":\"2021-09-30\"},\"results\":3,\"errors\":[]},{\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"cases\":{\"new\":\"+18578\",\"recovered\":20425139,\"total\":21445651,\"critical\":8318,\"active\":423257,\"1M_pop\":\"100007\"},\"tests\":{\"total\":57282520,\"1M_pop\":\"267124\"},\"time\":\"2021-10-01T23:45:02+00:00\",\"day\":\"2021-10-01\",\"deaths\":{\"new\":\"+455\",\"total\":597255,\"1M_pop\":\"2785\"},\"population\":214441931},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"cases\":{\"new\":\"+27527\",\"recovered\":20425139,\"total\":21427073,\"critical\":8318,\"active\":405134,\"1M_pop\":\"99920\"},\"tests\":{\"total\":57282520,\"1M_pop\":\"267124\"},\"time\":\"2021-10-01T21:00:03+00:00\",\"day\":\"2021-10-01\",\"deaths\":{\"new\":\"+637\",\"total\":596800,\"1M_pop\":\"2783\"},\"population\":214441931},{\"continent\":\"South-America\",\"country\":\"Brazil\",\"cases\":{\"new\":\"+27527\",\"recovered\":20425139,\"total\":21427073,\"critical\":8318,\"active\":405134,\"1M_pop\":\"99922\"},\"tests\":{\"total\":57282520,\"1M_pop\":\"267129\"},\"time\":\"2021-10-01T00:00:03+00:00\",\"day\":\"2021-10-01\",\"deaths\":{\"new\":\"+637\",\"total\":596800,\"1M_pop\":\"2783\"},\"population\":214437809}],\"get\":\"history\",\"parameters\":{\"country\":\"brazil\",\"day\":\"2021-10-01\"},\"results\":3,\"errors\":[]}]");
        String country = "brazil";

        when(client1.getCountryByRegionAndDate(country, startDay, endDay)).thenReturn(jsonArrayToListOfObjects(specific_country));

        mvc.perform(
            get("/api1/countries/brazil?startDay=" + startDay + "&endDay=" + endDay )
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].response[0].country", is("Brazil")))
        .andExpect(jsonPath("$[1].response[0].country", is("Brazil")));

        verify(client1, times(1)).getCountryByRegionAndDate(country, startDay, endDay);
    }

    @Test
    void getSpecificCountryAndDateRangeForAPI2() throws Exception {

        String startDay = "2021-09-30";
        String endDay = "2021-10-01";

        JSONArray specific_country = generateJSONArray("[{\"data\":[{\"date\":\"2021-09-30\",\"confirmed_diff\":1,\"active_diff\":0,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0209,\"last_update\":\"2021-10-01 04:21:35\",\"active\":86086,\"region\":{\"iso\":\"BRA\",\"province\":\"Acre\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.0238\",\"long\":\"-70.812\"},\"confirmed\":87924,\"deaths\":1838},{\"date\":\"2021-09-30\",\"confirmed_diff\":93,\"active_diff\":89,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0261,\"last_update\":\"2021-10-01 04:21:35\",\"active\":231997,\"region\":{\"iso\":\"BRA\",\"province\":\"Alagoas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.5713\",\"long\":\"-36.782\"},\"confirmed\":238210,\"deaths\":6213},{\"date\":\"2021-09-30\",\"confirmed_diff\":22,\"active_diff\":19,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0161,\"last_update\":\"2021-10-01 04:21:35\",\"active\":120855,\"region\":{\"iso\":\"BRA\",\"province\":\"Amapa\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"0.902\",\"long\":\"-52.003\"},\"confirmed\":122836,\"deaths\":1981},{\"date\":\"2021-09-30\",\"confirmed_diff\":41,\"active_diff\":41,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0322,\"last_update\":\"2021-10-01 04:21:35\",\"active\":412753,\"region\":{\"iso\":\"BRA\",\"province\":\"Amazonas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-3.4168\",\"long\":\"-65.8561\"},\"confirmed\":426476,\"deaths\":13723},{\"date\":\"2021-09-30\",\"confirmed_diff\":555,\"active_diff\":547,\"deaths_diff\":8,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0218,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1206940,\"region\":{\"iso\":\"BRA\",\"province\":\"Bahia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.5797\",\"long\":\"-41.7007\"},\"confirmed\":1233799,\"deaths\":26859},{\"date\":\"2021-09-30\",\"confirmed_diff\":10647,\"active_diff\":10636,\"deaths_diff\":11,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0258,\"last_update\":\"2021-10-01 04:21:35\",\"active\":916242,\"region\":{\"iso\":\"BRA\",\"province\":\"Ceara\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4984\",\"long\":\"-39.3206\"},\"confirmed\":940463,\"deaths\":24221},{\"date\":\"2021-09-30\",\"confirmed_diff\":950,\"active_diff\":940,\"deaths_diff\":10,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-01 04:21:35\",\"active\":484785,\"region\":{\"iso\":\"BRA\",\"province\":\"Distrito Federal\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.7998\",\"long\":\"-47.8645\"},\"confirmed\":495249,\"deaths\":10464},{\"date\":\"2021-09-30\",\"confirmed_diff\":956,\"active_diff\":945,\"deaths_diff\":11,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0214,\"last_update\":\"2021-10-01 04:21:35\",\"active\":573481,\"region\":{\"iso\":\"BRA\",\"province\":\"Espirito Santo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-19.1834\",\"long\":\"-40.3089\"},\"confirmed\":586039,\"deaths\":12558},{\"date\":\"2021-09-30\",\"confirmed_diff\":1662,\"active_diff\":1626,\"deaths_diff\":36,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0272,\"last_update\":\"2021-10-01 04:21:35\",\"active\":840125,\"region\":{\"iso\":\"BRA\",\"province\":\"Goias\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.827\",\"long\":\"-49.8362\"},\"confirmed\":863618,\"deaths\":23493},{\"date\":\"2021-09-30\",\"confirmed_diff\":354,\"active_diff\":349,\"deaths_diff\":5,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0286,\"last_update\":\"2021-10-01 04:21:35\",\"active\":346062,\"region\":{\"iso\":\"BRA\",\"province\":\"Maranhao\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-4.9609\",\"long\":\"-45.2744\"},\"confirmed\":356236,\"deaths\":10174},{\"date\":\"2021-09-30\",\"confirmed_diff\":427,\"active_diff\":421,\"deaths_diff\":6,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0254,\"last_update\":\"2021-10-01 04:21:35\",\"active\":520328,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.6819\",\"long\":\"-56.9211\"},\"confirmed\":533891,\"deaths\":13563},{\"date\":\"2021-09-30\",\"confirmed_diff\":104,\"active_diff\":100,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0256,\"last_update\":\"2021-10-01 04:21:35\",\"active\":363362,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-20.7722\",\"long\":\"-54.7852\"},\"confirmed\":372917,\"deaths\":9555},{\"date\":\"2021-09-30\",\"confirmed_diff\":2414,\"active_diff\":2333,\"deaths_diff\":81,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0255,\"last_update\":\"2021-10-01 04:21:35\",\"active\":2085831,\"region\":{\"iso\":\"BRA\",\"province\":\"Minas Gerais\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-18.5122\",\"long\":\"-44.555\"},\"confirmed\":2140378,\"deaths\":54547},{\"date\":\"2021-09-30\",\"confirmed_diff\":297,\"active_diff\":292,\"deaths_diff\":5,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0282,\"last_update\":\"2021-10-01 04:21:35\",\"active\":574660,\"region\":{\"iso\":\"BRA\",\"province\":\"Para\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-1.9981\",\"long\":\"-54.9306\"},\"confirmed\":591318,\"deaths\":16658},{\"date\":\"2021-09-30\",\"confirmed_diff\":216,\"active_diff\":213,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-01 04:21:35\",\"active\":432363,\"region\":{\"iso\":\"BRA\",\"province\":\"Paraiba\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.24\",\"long\":\"-36.782\"},\"confirmed\":441674,\"deaths\":9311},{\"date\":\"2021-09-30\",\"confirmed_diff\":1807,\"active_diff\":1752,\"deaths_diff\":55,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0259,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1472413,\"region\":{\"iso\":\"BRA\",\"province\":\"Parana\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-25.2521\",\"long\":\"-52.0215\"},\"confirmed\":1511509,\"deaths\":39096},{\"date\":\"2021-09-30\",\"confirmed_diff\":549,\"active_diff\":533,\"deaths_diff\":16,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0318,\"last_update\":\"2021-10-01 04:21:35\",\"active\":600983,\"region\":{\"iso\":\"BRA\",\"province\":\"Pernambuco\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-8.8137\",\"long\":\"-36.9541\"},\"confirmed\":620723,\"deaths\":19740},{\"date\":\"2021-09-30\",\"confirmed_diff\":52,\"active_diff\":48,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.022,\"last_update\":\"2021-10-01 04:21:35\",\"active\":312323,\"region\":{\"iso\":\"BRA\",\"province\":\"Piaui\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.7183\",\"long\":\"-42.7289\"},\"confirmed\":319334,\"deaths\":7011},{\"date\":\"2021-09-30\",\"confirmed_diff\":119,\"active_diff\":118,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0199,\"last_update\":\"2021-10-01 04:21:35\",\"active\":361282,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Norte\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4026\",\"long\":\"-36.9541\"},\"confirmed\":368619,\"deaths\":7337},{\"date\":\"2021-09-30\",\"confirmed_diff\":1033,\"active_diff\":1000,\"deaths_diff\":33,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0243,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1402102,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-30.0346\",\"long\":\"-51.2177\"},\"confirmed\":1436962,\"deaths\":34860},{\"date\":\"2021-09-30\",\"confirmed_diff\":2277,\"active_diff\":2158,\"deaths_diff\":119,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0514,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1219597,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio de Janeiro\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-22.9068\",\"long\":\"-43.1729\"},\"confirmed\":1285731,\"deaths\":66134},{\"date\":\"2021-09-30\",\"confirmed_diff\":48,\"active_diff\":44,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0246,\"last_update\":\"2021-10-01 04:21:35\",\"active\":259350,\"region\":{\"iso\":\"BRA\",\"province\":\"Rondonia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-11.5057\",\"long\":\"-63.5806\"},\"confirmed\":265879,\"deaths\":6529},{\"date\":\"2021-09-30\",\"confirmed_diff\":13,\"active_diff\":10,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0159,\"last_update\":\"2021-10-01 04:21:35\",\"active\":124211,\"region\":{\"iso\":\"BRA\",\"province\":\"Roraima\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-2.7376\",\"long\":\"-62.0751\"},\"confirmed\":126212,\"deaths\":2001},{\"date\":\"2021-09-30\",\"confirmed_diff\":1121,\"active_diff\":1110,\"deaths_diff\":11,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0162,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1173145,\"region\":{\"iso\":\"BRA\",\"province\":\"Santa Catarina\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-27.2423\",\"long\":\"-50.2189\"},\"confirmed\":1192421,\"deaths\":19276},{\"date\":\"2021-09-30\",\"confirmed_diff\":1550,\"active_diff\":1361,\"deaths_diff\":189,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0343,\"last_update\":\"2021-10-01 04:21:35\",\"active\":4216322,\"region\":{\"iso\":\"BRA\",\"province\":\"Sao Paulo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-23.5505\",\"long\":\"-46.6333\"},\"confirmed\":4366132,\"deaths\":149810},{\"date\":\"2021-09-30\",\"confirmed_diff\":24,\"active_diff\":23,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0216,\"last_update\":\"2021-10-01 04:21:35\",\"active\":272094,\"region\":{\"iso\":\"BRA\",\"province\":\"Sergipe\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.5741\",\"long\":\"-37.3857\"},\"confirmed\":278104,\"deaths\":6010},{\"date\":\"2021-09-30\",\"confirmed_diff\":195,\"active_diff\":192,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0169,\"last_update\":\"2021-10-01 04:21:35\",\"active\":220632,\"region\":{\"iso\":\"BRA\",\"province\":\"Tocantins\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.1753\",\"long\":\"-48.2982\"},\"confirmed\":224419,\"deaths\":3787}]},{\"data\":[{\"date\":\"2021-10-01\",\"confirmed_diff\":1,\"active_diff\":1,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0209,\"last_update\":\"2021-10-02 04:21:25\",\"active\":86087,\"region\":{\"iso\":\"BRA\",\"province\":\"Acre\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.0238\",\"long\":\"-70.812\"},\"confirmed\":87925,\"deaths\":1838},{\"date\":\"2021-10-01\",\"confirmed_diff\":39,\"active_diff\":35,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0261,\"last_update\":\"2021-10-02 04:21:25\",\"active\":232032,\"region\":{\"iso\":\"BRA\",\"province\":\"Alagoas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.5713\",\"long\":\"-36.782\"},\"confirmed\":238249,\"deaths\":6217},{\"date\":\"2021-10-01\",\"confirmed_diff\":22,\"active_diff\":19,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0161,\"last_update\":\"2021-10-02 04:21:25\",\"active\":120874,\"region\":{\"iso\":\"BRA\",\"province\":\"Amapa\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"0.902\",\"long\":\"-52.003\"},\"confirmed\":122858,\"deaths\":1984},{\"date\":\"2021-10-01\",\"confirmed_diff\":51,\"active_diff\":50,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0322,\"last_update\":\"2021-10-02 04:21:25\",\"active\":412803,\"region\":{\"iso\":\"BRA\",\"province\":\"Amazonas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-3.4168\",\"long\":\"-65.8561\"},\"confirmed\":426527,\"deaths\":13724},{\"date\":\"2021-10-01\",\"confirmed_diff\":571,\"active_diff\":564,\"deaths_diff\":7,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0218,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1207504,\"region\":{\"iso\":\"BRA\",\"province\":\"Bahia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.5797\",\"long\":\"-41.7007\"},\"confirmed\":1234370,\"deaths\":26866},{\"date\":\"2021-10-01\",\"confirmed_diff\":293,\"active_diff\":281,\"deaths_diff\":12,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0258,\"last_update\":\"2021-10-02 04:21:25\",\"active\":916523,\"region\":{\"iso\":\"BRA\",\"province\":\"Ceara\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4984\",\"long\":\"-39.3206\"},\"confirmed\":940756,\"deaths\":24233},{\"date\":\"2021-10-01\",\"confirmed_diff\":937,\"active_diff\":921,\"deaths_diff\":16,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-02 04:21:25\",\"active\":485706,\"region\":{\"iso\":\"BRA\",\"province\":\"Distrito Federal\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.7998\",\"long\":\"-47.8645\"},\"confirmed\":496186,\"deaths\":10480},{\"date\":\"2021-10-01\",\"confirmed_diff\":963,\"active_diff\":960,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0214,\"last_update\":\"2021-10-02 04:21:25\",\"active\":574441,\"region\":{\"iso\":\"BRA\",\"province\":\"Espirito Santo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-19.1834\",\"long\":\"-40.3089\"},\"confirmed\":587002,\"deaths\":12561},{\"date\":\"2021-10-01\",\"confirmed_diff\":1541,\"active_diff\":1501,\"deaths_diff\":40,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0272,\"last_update\":\"2021-10-02 04:21:25\",\"active\":841626,\"region\":{\"iso\":\"BRA\",\"province\":\"Goias\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.827\",\"long\":\"-49.8362\"},\"confirmed\":865159,\"deaths\":23533},{\"date\":\"2021-10-01\",\"confirmed_diff\":326,\"active_diff\":323,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0285,\"last_update\":\"2021-10-02 04:21:25\",\"active\":346385,\"region\":{\"iso\":\"BRA\",\"province\":\"Maranhao\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-4.9609\",\"long\":\"-45.2744\"},\"confirmed\":356562,\"deaths\":10177},{\"date\":\"2021-10-01\",\"confirmed_diff\":445,\"active_diff\":440,\"deaths_diff\":5,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0254,\"last_update\":\"2021-10-02 04:21:25\",\"active\":520768,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.6819\",\"long\":\"-56.9211\"},\"confirmed\":534336,\"deaths\":13568},{\"date\":\"2021-10-01\",\"confirmed_diff\":167,\"active_diff\":158,\"deaths_diff\":9,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0256,\"last_update\":\"2021-10-02 04:21:25\",\"active\":363520,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-20.7722\",\"long\":\"-54.7852\"},\"confirmed\":373084,\"deaths\":9564},{\"date\":\"2021-10-01\",\"confirmed_diff\":2856,\"active_diff\":2790,\"deaths_diff\":66,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0255,\"last_update\":\"2021-10-02 04:21:25\",\"active\":2088621,\"region\":{\"iso\":\"BRA\",\"province\":\"Minas Gerais\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-18.5122\",\"long\":\"-44.555\"},\"confirmed\":2143234,\"deaths\":54613},{\"date\":\"2021-10-01\",\"confirmed_diff\":256,\"active_diff\":254,\"deaths_diff\":2,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0282,\"last_update\":\"2021-10-02 04:21:25\",\"active\":574914,\"region\":{\"iso\":\"BRA\",\"province\":\"Para\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-1.9981\",\"long\":\"-54.9306\"},\"confirmed\":591574,\"deaths\":16660},{\"date\":\"2021-10-01\",\"confirmed_diff\":163,\"active_diff\":162,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-02 04:21:25\",\"active\":432525,\"region\":{\"iso\":\"BRA\",\"province\":\"Paraiba\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.24\",\"long\":\"-36.782\"},\"confirmed\":441837,\"deaths\":9312},{\"date\":\"2021-10-01\",\"confirmed_diff\":1792,\"active_diff\":1779,\"deaths_diff\":13,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0258,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1474192,\"region\":{\"iso\":\"BRA\",\"province\":\"Parana\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-25.2521\",\"long\":\"-52.0215\"},\"confirmed\":1513301,\"deaths\":39109},{\"date\":\"2021-10-01\",\"confirmed_diff\":469,\"active_diff\":462,\"deaths_diff\":7,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0318,\"last_update\":\"2021-10-02 04:21:25\",\"active\":601445,\"region\":{\"iso\":\"BRA\",\"province\":\"Pernambuco\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-8.8137\",\"long\":\"-36.9541\"},\"confirmed\":621192,\"deaths\":19747},{\"date\":\"2021-10-01\",\"confirmed_diff\":76,\"active_diff\":75,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.022,\"last_update\":\"2021-10-02 04:21:25\",\"active\":312398,\"region\":{\"iso\":\"BRA\",\"province\":\"Piaui\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.7183\",\"long\":\"-42.7289\"},\"confirmed\":319410,\"deaths\":7012},{\"date\":\"2021-10-01\",\"confirmed_diff\":97,\"active_diff\":94,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0199,\"last_update\":\"2021-10-02 04:21:25\",\"active\":361376,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Norte\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4026\",\"long\":\"-36.9541\"},\"confirmed\":368716,\"deaths\":7340},{\"date\":\"2021-10-01\",\"confirmed_diff\":1916,\"active_diff\":1895,\"deaths_diff\":21,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0242,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1403997,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-30.0346\",\"long\":\"-51.2177\"},\"confirmed\":1438878,\"deaths\":34881},{\"date\":\"2021-10-01\",\"confirmed_diff\":2745,\"active_diff\":2618,\"deaths_diff\":127,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0514,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1222215,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio de Janeiro\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-22.9068\",\"long\":\"-43.1729\"},\"confirmed\":1288476,\"deaths\":66261},{\"date\":\"2021-10-01\",\"confirmed_diff\":0,\"active_diff\":0,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0246,\"last_update\":\"2021-10-02 04:21:25\",\"active\":259350,\"region\":{\"iso\":\"BRA\",\"province\":\"Rondonia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-11.5057\",\"long\":\"-63.5806\"},\"confirmed\":265879,\"deaths\":6529},{\"date\":\"2021-10-01\",\"confirmed_diff\":8,\"active_diff\":8,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0159,\"last_update\":\"2021-10-02 04:21:25\",\"active\":124219,\"region\":{\"iso\":\"BRA\",\"province\":\"Roraima\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-2.7376\",\"long\":\"-62.0751\"},\"confirmed\":126220,\"deaths\":2001},{\"date\":\"2021-10-01\",\"confirmed_diff\":960,\"active_diff\":945,\"deaths_diff\":15,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0162,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1174090,\"region\":{\"iso\":\"BRA\",\"province\":\"Santa Catarina\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-27.2423\",\"long\":\"-50.2189\"},\"confirmed\":1193381,\"deaths\":19291},{\"date\":\"2021-10-01\",\"confirmed_diff\":1616,\"active_diff\":1473,\"deaths_diff\":143,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0343,\"last_update\":\"2021-10-02 04:21:25\",\"active\":4217795,\"region\":{\"iso\":\"BRA\",\"province\":\"Sao Paulo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-23.5505\",\"long\":\"-46.6333\"},\"confirmed\":4367748,\"deaths\":149953},{\"date\":\"2021-10-01\",\"confirmed_diff\":30,\"active_diff\":30,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0216,\"last_update\":\"2021-10-02 04:21:25\",\"active\":272124,\"region\":{\"iso\":\"BRA\",\"province\":\"Sergipe\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.5741\",\"long\":\"-37.3857\"},\"confirmed\":278134,\"deaths\":6010},{\"date\":\"2021-10-01\",\"confirmed_diff\":238,\"active_diff\":234,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0169,\"last_update\":\"2021-10-02 04:21:25\",\"active\":220866,\"region\":{\"iso\":\"BRA\",\"province\":\"Tocantins\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.1753\",\"long\":\"-48.2982\"},\"confirmed\":224657,\"deaths\":3791}]}]");
        String country = "brazil";

        when(client2.getCountryByRegionAndDate(country, startDay, endDay)).thenReturn(jsonArrayToListOfObjects(specific_country));

        mvc.perform(
            get("/api2/countries/brazil?startDay=" + startDay + "&endDay=" + endDay )
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].data[0].date", is("2021-09-30")))
        .andExpect(jsonPath("$[0].data[0].region.name", is("Brazil")))
        .andExpect(jsonPath("$[1].data[0].date", is("2021-10-01")))
        .andExpect(jsonPath("$[1].data[0].region.name", is("Brazil")));

        verify(client2, times(1)).getCountryByRegionAndDate(country, startDay, endDay);
    }

    @Test
    void getSpecificCountryNotFoundForAPI1() throws Exception {

        JSONArray country_not_found = generateJSONArray("[{\"get\":\"history\",\"parameters\":{\"country\":\"not_existent\"},\"errors\":[],\"results\":0,\"response\":[]}]");
        String country = "not_existent";

        when(client1.getCountryByRegion(country)).thenReturn(jsonArrayToListOfObjects(country_not_found));

        mvc.perform(
            get("/api1/countries/not_existent")
        )
        .andExpect(jsonPath("$[0].results", is(0)));

        verify(client1, times(1)).getCountryByRegion(country);
    }

    @Test
    void getSpecificCountryNotFoundForAPI2() throws Exception {

        JSONArray country_not_found = generateJSONArray("[{\"data\":[]}]");
        String country = "not_existent";

        when(client2.getCountryByRegion(country)).thenReturn(jsonArrayToListOfObjects(country_not_found));

        mvc.perform(
            get("/api2/countries/not_existent")
        )
        .andExpect(jsonPath("$[0].data", is(empty())));

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
