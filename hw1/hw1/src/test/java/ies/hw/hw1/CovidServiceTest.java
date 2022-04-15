// package ies.hw.hw1;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.internal.verification.VerificationModeFactory;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import ies.hw.hw1.http.BasicHttpClient;
// import ies.hw.hw1.models.Cache;
// import ies.hw.hw1.service.CovidService;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.when;

// @ExtendWith(MockitoExtension.class)
// public class CovidServiceTest {

//     @Mock(lenient = true)
//     private BasicHttpClient client;

//     @InjectMocks
//     private CovidService service;

//     final ResponseEntity<String> ALL_COUNTRIES = new ResponseEntity<>(
//         "{\"get\":\"countries\",\"parameters\":[],\"errors\":[],\"results\":233,\"response\":[\"Afghanistan\",\"Albania\",\"Algeria\",\"Andorra\",\"Angola\",\"Anguilla\",\"Antigua-and-Barbuda\",\"Argentina\",\"Armenia\",\"Aruba\",\"Australia\",\"Austria\",\"Azerbaijan\",\"Bahamas\",\"Bahrain\",\"Bangladesh\",\"Barbados\",\"Belarus\",\"Belgium\",\"Belize\",\"Benin\",\"Bermuda\",\"Bhutan\",\"Bolivia\",\"Bosnia-and-Herzegovina\",\"Botswana\",\"Brazil\",\"British-Virgin-Islands\",\"Brunei\",\"Bulgaria\",\"Burkina-Faso\",\"Burundi\",\"Cabo-Verde\",\"Cambodia\",\"Cameroon\",\"Canada\",\"CAR\",\"Caribbean-Netherlands\",\"Cayman-Islands\",\"Chad\",\"Channel-Islands\",\"Chile\",\"China\",\"Colombia\",\"Comoros\",\"Congo\",\"Cook-Islands\",\"Costa-Rica\",\"Croatia\",\"Cuba\",\"Cura&ccedil;ao\",\"Cyprus\",\"Czechia\",\"Denmark\",\"Diamond-Princess\",\"Diamond-Princess-\",\"Djibouti\",\"Dominica\",\"Dominican-Republic\",\"DRC\",\"Ecuador\",\"Egypt\",\"El-Salvador\",\"Equatorial-Guinea\",\"Eritrea\",\"Estonia\",\"Eswatini\",\"Ethiopia\",\"Faeroe-Islands\",\"Falkland-Islands\",\"Fiji\",\"Finland\",\"France\",\"French-Guiana\",\"French-Polynesia\",\"Gabon\",\"Gambia\",\"Georgia\",\"Germany\",\"Ghana\",\"Gibraltar\",\"Greece\",\"Greenland\",\"Grenada\",\"Guadeloupe\",\"Guam\",\"Guatemala\",\"Guinea\",\"Guinea-Bissau\",\"Guyana\",\"Haiti\",\"Honduras\",\"Hong-Kong\",\"Hungary\",\"Iceland\",\"India\",\"Indonesia\",\"Iran\",\"Iraq\",\"Ireland\",\"Isle-of-Man\",\"Israel\",\"Italy\",\"Ivory-Coast\",\"Jamaica\",\"Japan\",\"Jordan\",\"Kazakhstan\",\"Kenya\",\"Kiribati\",\"Kuwait\",\"Kyrgyzstan\",\"Laos\",\"Latvia\",\"Lebanon\",\"Lesotho\",\"Liberia\",\"Libya\",\"Liechtenstein\",\"Lithuania\",\"Luxembourg\",\"Macao\",\"Madagascar\",\"Malawi\",\"Malaysia\",\"Maldives\",\"Mali\",\"Malta\",\"Marshall-Islands\",\"Martinique\",\"Mauritania\",\"Mauritius\",\"Mayotte\",\"Mexico\",\"Micronesia\",\"Moldova\",\"Monaco\",\"Mongolia\",\"Montenegro\",\"Montserrat\",\"Morocco\",\"Mozambique\",\"MS-Zaandam\",\"MS-Zaandam-\",\"Myanmar\",\"Namibia\",\"Nauru\",\"Nepal\",\"Netherlands\",\"New-Caledonia\",\"New-Zealand\",\"Nicaragua\",\"Niger\",\"Nigeria\",\"Niue\",\"North-Macedonia\",\"Norway\",\"Oman\",\"Pakistan\",\"Palau\",\"Palestine\",\"Panama\",\"Papua-New-Guinea\",\"Paraguay\",\"Peru\",\"Philippines\",\"Poland\",\"Portugal\",\"Puerto-Rico\",\"Qatar\",\"R&eacute;union\",\"Romania\",\"Russia\",\"Rwanda\",\"S-Korea\",\"Saint-Helena\",\"Saint-Kitts-and-Nevis\",\"Saint-Lucia\",\"Saint-Martin\",\"Saint-Pierre-Miquelon\",\"Samoa\",\"San-Marino\",\"Sao-Tome-and-Principe\",\"Saudi-Arabia\",\"Senegal\",\"Serbia\",\"Seychelles\",\"Sierra-Leone\",\"Singapore\",\"Sint-Maarten\",\"Slovakia\",\"Slovenia\",\"Solomon-Islands\",\"Somalia\",\"South-Africa\",\"South-Sudan\",\"Spain\",\"Sri-Lanka\",\"St-Barth\",\"St-Vincent-Grenadines\",\"Sudan\",\"Suriname\",\"Sweden\",\"Switzerland\",\"Syria\",\"Taiwan\",\"Tajikistan\",\"Tanzania\",\"Thailand\",\"Timor-Leste\",\"Togo\",\"Tonga\",\"Trinidad-and-Tobago\",\"Tunisia\",\"Turkey\",\"Turks-and-Caicos\",\"UAE\",\"Uganda\",\"UK\",\"Ukraine\",\"Uruguay\",\"US-Virgin-Islands\",\"USA\",\"Uzbekistan\",\"Vanuatu\",\"Vatican-City\",\"Venezuela\",\"Vietnam\",\"Wallis-and-Futuna\",\"Western-Sahara\",\"Yemen\",\"Zambia\",\"Zimbabwe\"]}",
//         HttpStatus.OK
//         );

//     final ResponseEntity<String> SPECIFIC_COUNTRY = new ResponseEntity<>(
//         "{\"get\":\"history\",\"parameters\":{\"country\":\"brazil\"},\"errors\":[],\"results\":1,\"response\":[{\"continent\":\"South-America\",\"country\":\"Brazil\",\"population\":215245721,\"cases\":{\"new\":\"+26924\",\"active\":381625,\"critical\":8318,\"recovered\":29167518,\"1M_pop\":\"140355\",\"total\":30210853},\"deaths\":{\"new\":\"+158\",\"1M_pop\":\"3074\",\"total\":661710},\"tests\":{\"1M_pop\":\"296295\",\"total\":63776166},\"day\":\"2022-04-14\",\"time\":\"2022-04-14T15:15:03+00:00\"}]}",
//         HttpStatus.OK
//     );

//     final Cache CACHE_USAGE = new Cache(2, 1, 1000000);

//     final ResponseEntity<String> COUNTRY_NOT_FOUND = new ResponseEntity<>(
//         "{\"get\":\"history\",\"parameters\":{\"country\":\"not_existent\"},\"errors\":[],\"results\":0,\"response\":[]}",
//         HttpStatus.OK
//     );
    
    
//     @Test
//     void getAllCars() {
//         when(client.getAllCountries()).thenReturn(ALL_COUNTRIES);

//         ResponseEntity<String> response = client.getAllCountries();


//     }
// }
