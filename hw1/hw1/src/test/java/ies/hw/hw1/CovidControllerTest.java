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
import ies.hw.hw1.service.CovidService;
import ies.hw.hw1.models.Cache;

import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.when;

@WebMvcTest(CovidController.class)
public class CovidControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean 
    private CovidService service;

    final ResponseEntity<String> ALL_COUNTRIES = new ResponseEntity<>(
        "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Afghanistan\",\"Albania\",\"Algeria\",\"Andorra\",\"Angola\",\"Anguilla\",\"Antigua-and-Barbuda\",\"Argentina\",\"Armenia\",\"Aruba\",\"Australia\",\"Austria\",\"Azerbaijan\",\"Bahamas\",\"Bahrain\",\"Bangladesh\",\"Barbados\",\"Belarus\",\"Belgium\",\"Belize\",\"Benin\",\"Bermuda\",\"Bhutan\",\"Bolivia\",\"Bosnia-and-Herzegovina\",\"Botswana\",\"Brazil\",\"British-Virgin-Islands\",\"Brunei\",\"Bulgaria\",\"Burkina-Faso\",\"Burundi\",\"Cabo-Verde\",\"Cambodia\",\"Cameroon\",\"Canada\",\"CAR\",\"Caribbean-Netherlands\",\"Cayman-Islands\",\"Chad\",\"Channel-Islands\",\"Chile\",\"China\",\"Colombia\",\"Comoros\",\"Congo\",\"Cook-Islands\",\"Costa-Rica\",\"Croatia\",\"Cuba\",\"Cura&ccedil;ao\",\"Cyprus\",\"Czechia\",\"Denmark\",\"Diamond-Princess\",\"Diamond-Princess-\",\"Djibouti\",\"Dominica\",\"Dominican-Republic\",\"DRC\",\"Ecuador\",\"Egypt\",\"El-Salvador\",\"Equatorial-Guinea\",\"Eritrea\",\"Estonia\",\"Eswatini\",\"Ethiopia\",\"Faeroe-Islands\",\"Falkland-Islands\",\"Fiji\",\"Finland\",\"France\",\"French-Guiana\",\"French-Polynesia\",\"Gabon\",\"Gambia\",\"Georgia\",\"Germany\",\"Ghana\",\"Gibraltar\",\"Greece\",\"Greenland\",\"Grenada\",\"Guadeloupe\",\"Guam\",\"Guatemala\",\"Guinea\",\"Guinea-Bissau\",\"Guyana\",\"Haiti\",\"Honduras\",\"Hong-Kong\",\"Hungary\",\"Iceland\",\"India\",\"Indonesia\",\"Iran\",\"Iraq\",\"Ireland\",\"Isle-of-Man\",\"Israel\",\"Italy\",\"Ivory-Coast\",\"Jamaica\",\"Japan\",\"Jordan\",\"Kazakhstan\",\"Kenya\",\"Kiribati\",\"Kuwait\",\"Kyrgyzstan\",\"Laos\",\"Latvia\",\"Lebanon\",\"Lesotho\",\"Liberia\",\"Libya\",\"Liechtenstein\",\"Lithuania\",\"Luxembourg\",\"Macao\",\"Madagascar\",\"Malawi\",\"Malaysia\",\"Maldives\",\"Mali\",\"Malta\",\"Marshall-Islands\",\"Martinique\",\"Mauritania\",\"Mauritius\",\"Mayotte\",\"Mexico\",\"Micronesia\",\"Moldova\",\"Monaco\",\"Mongolia\",\"Montenegro\",\"Montserrat\",\"Morocco\",\"Mozambique\",\"MS-Zaandam\",\"MS-Zaandam-\",\"Myanmar\",\"Namibia\",\"Nauru\",\"Nepal\",\"Netherlands\",\"New-Caledonia\",\"New-Zealand\",\"Nicaragua\",\"Niger\",\"Nigeria\",\"Niue\",\"North-Macedonia\",\"Norway\",\"Oman\",\"Pakistan\",\"Palau\",\"Palestine\",\"Panama\",\"Papua-New-Guinea\",\"Paraguay\",\"Peru\",\"Philippines\",\"Poland\",\"Portugal\",\"Puerto-Rico\",\"Qatar\",\"R&eacute;union\",\"Romania\",\"Russia\",\"Rwanda\",\"S-Korea\",\"Saint-Helena\",\"Saint-Kitts-and-Nevis\",\"Saint-Lucia\",\"Saint-Martin\",\"Saint-Pierre-Miquelon\",\"Samoa\",\"San-Marino\",\"Sao-Tome-and-Principe\",\"Saudi-Arabia\",\"Senegal\",\"Serbia\",\"Seychelles\",\"Sierra-Leone\",\"Singapore\",\"Sint-Maarten\",\"Slovakia\",\"Slovenia\",\"Solomon-Islands\",\"Somalia\",\"South-Africa\",\"South-Sudan\",\"Spain\",\"Sri-Lanka\",\"St-Barth\",\"St-Vincent-Grenadines\",\"Sudan\",\"Suriname\",\"Sweden\",\"Switzerland\",\"Syria\",\"Taiwan\",\"Tajikistan\",\"Tanzania\",\"Thailand\",\"Timor-Leste\",\"Togo\",\"Tonga\",\"Trinidad-and-Tobago\",\"Tunisia\",\"Turkey\",\"Turks-and-Caicos\",\"UAE\",\"Uganda\",\"UK\",\"Ukraine\",\"Uruguay\",\"US-Virgin-Islands\",\"USA\",\"Uzbekistan\",\"Vanuatu\",\"Vatican-City\",\"Venezuela\",\"Vietnam\",\"Wallis-and-Futuna\",\"Western-Sahara\",\"Yemen\",\"Zambia\",\"Zimbabwe\"]}",
        HttpStatus.OK
        );

    final ResponseEntity<String> SPECIFIC_COUNTRY = new ResponseEntity<>(
        "{\"get\":\"history\",\"parameters\":{\"country\":\"brazil\"},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215245721,\"cases\":{\"new\":\"+26924\",\"active\":381625,\"critical\":8318,\"recovered\":29167518,\"1M_pop\":\"140355\",\"total\":30210853},\"deaths\":{\"new\":\"+158\",\"1M_pop\":\"3074\",\"total\":661710},\"tests\":{\"1M_pop\":\"296295\",\"total\":63776166},\"day\":\"2022-04-14\",\"time\":\"2022-04-14T15:15:03+00:00\"}]}",
        HttpStatus.OK
    );

    final Cache CACHE_USAGE = new Cache(2, 1, 1000000);


    final ResponseEntity<String> URL_NOT_FOUND = new ResponseEntity<>(
        "{\"timestamp\":\"2022-04-14T15:35:58.815+00:00\",\"status\":404,\"error\":\"Not Found\",\"path\":\"/randompage\"}",
        HttpStatus.OK
    );

    final ResponseEntity<String> COUNTRY_NOT_FOUND = new ResponseEntity<>(
        "{\"get\":\"history\",\"parameters\":{\"country\":\"not_existent\"},\"errors\":[],\"results\":0,\"response\":[]}",
        HttpStatus.OK
    );

    @Test
    void getAllCountriesSuccess() throws Exception {

        when(service.getAllCountries()).thenReturn(ALL_COUNTRIES);

        mvc.perform(
            get("/countries")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", is(233)));

        verify(service, times(1)).getAllCountries();
    }

    @Test
    void getSpecificCountry() throws Exception {

        String country = "brazil";

        when(service.getStatsByCountry(country)).thenReturn(SPECIFIC_COUNTRY);

        mvc.perform(
            get("/countries/brazil")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results", is(1)))
        .andExpect(jsonPath("$.response[0].country", is("Brazil")));

        verify(service, times(1)).getStatsByCountry(country);
    }

    @Test
    void getCacheUsage() throws Exception {

        when(service.getCacheInfo()).thenReturn(CACHE_USAGE);

        mvc.perform(
            get("/cache/usage")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.numberOfHits", is(2)))
        .andExpect(jsonPath("$.numberOfMisses", is(1)))
        .andExpect(jsonPath("$.numberOfRequests", is(3)));
    

        // "{\"numberOfHits\":2,\"numberOfMisses\":1,\"ttl\":1000000,\"numberOfRequests\":3}",


        verify(service, times(1)).getCacheInfo();
    }

}
