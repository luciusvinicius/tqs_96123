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

public class API2Test {

    @Mock
    Client client;

    private API2 api = new API2();

    private final String BASE_URL = "https://covid-19-statistics.p.rapidapi.com/";
    private final String REPORTS_URL = BASE_URL + "reports";
    private final String REGIONS_URL = BASE_URL + "regions";

    private final String ALL_COUNTRIES = "{\"data\":[{\"iso\":\"CHN\",\"name\":\"China\"},{\"iso\":\"TWN\",\"name\":\"Taipei and environs\"},{\"iso\":\"USA\",\"name\":\"US\"},{\"iso\":\"JPN\",\"name\":\"Japan\"},{\"iso\":\"THA\",\"name\":\"Thailand\"},{\"iso\":\"KOR\",\"name\":\"Korea, South\"},{\"iso\":\"SGP\",\"name\":\"Singapore\"},{\"iso\":\"PHL\",\"name\":\"Philippines\"},{\"iso\":\"MYS\",\"name\":\"Malaysia\"},{\"iso\":\"VNM\",\"name\":\"Vietnam\"},{\"iso\":\"AUS\",\"name\":\"Australia\"},{\"iso\":\"MEX\",\"name\":\"Mexico\"},{\"iso\":\"BRA\",\"name\":\"Brazil\"},{\"iso\":\"COL\",\"name\":\"Colombia\"}]}";
    private final String SPECIFIC_COUNTRY = "{\"data\":[{\"date\":\"2022-04-16\",\"confirmed_diff\":0,\"active_diff\":0,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0161,\"last_update\":\"2022-04-17 04:20:59\",\"active\":122358,\"region\":{\"iso\":\"BRA\",\"province\":\"Acre\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.0238\",\"long\":\"-70.812\"},\"confirmed\":124354,\"deaths\":1996}]}";
    private final String SPECIFIC_COUNTRY_WITH_START_DATE = "{\"data\":[{\"date\":\"2021-09-30\",\"confirmed_diff\":1,\"active_diff\":0,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0209,\"last_update\":\"2021-10-01 04:21:35\",\"active\":86086,\"region\":{\"iso\":\"BRA\",\"province\":\"Acre\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.0238\",\"long\":\"-70.812\"},\"confirmed\":87924,\"deaths\":1838},{\"date\":\"2021-09-30\",\"confirmed_diff\":93,\"active_diff\":89,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0261,\"last_update\":\"2021-10-01 04:21:35\",\"active\":231997,\"region\":{\"iso\":\"BRA\",\"province\":\"Alagoas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.5713\",\"long\":\"-36.782\"},\"confirmed\":238210,\"deaths\":6213},{\"date\":\"2021-09-30\",\"confirmed_diff\":22,\"active_diff\":19,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0161,\"last_update\":\"2021-10-01 04:21:35\",\"active\":120855,\"region\":{\"iso\":\"BRA\",\"province\":\"Amapa\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"0.902\",\"long\":\"-52.003\"},\"confirmed\":122836,\"deaths\":1981},{\"date\":\"2021-09-30\",\"confirmed_diff\":41,\"active_diff\":41,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0322,\"last_update\":\"2021-10-01 04:21:35\",\"active\":412753,\"region\":{\"iso\":\"BRA\",\"province\":\"Amazonas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-3.4168\",\"long\":\"-65.8561\"},\"confirmed\":426476,\"deaths\":13723},{\"date\":\"2021-09-30\",\"confirmed_diff\":555,\"active_diff\":547,\"deaths_diff\":8,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0218,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1206940,\"region\":{\"iso\":\"BRA\",\"province\":\"Bahia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.5797\",\"long\":\"-41.7007\"},\"confirmed\":1233799,\"deaths\":26859},{\"date\":\"2021-09-30\",\"confirmed_diff\":10647,\"active_diff\":10636,\"deaths_diff\":11,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0258,\"last_update\":\"2021-10-01 04:21:35\",\"active\":916242,\"region\":{\"iso\":\"BRA\",\"province\":\"Ceara\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4984\",\"long\":\"-39.3206\"},\"confirmed\":940463,\"deaths\":24221},{\"date\":\"2021-09-30\",\"confirmed_diff\":950,\"active_diff\":940,\"deaths_diff\":10,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-01 04:21:35\",\"active\":484785,\"region\":{\"iso\":\"BRA\",\"province\":\"Distrito Federal\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.7998\",\"long\":\"-47.8645\"},\"confirmed\":495249,\"deaths\":10464},{\"date\":\"2021-09-30\",\"confirmed_diff\":956,\"active_diff\":945,\"deaths_diff\":11,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0214,\"last_update\":\"2021-10-01 04:21:35\",\"active\":573481,\"region\":{\"iso\":\"BRA\",\"province\":\"Espirito Santo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-19.1834\",\"long\":\"-40.3089\"},\"confirmed\":586039,\"deaths\":12558},{\"date\":\"2021-09-30\",\"confirmed_diff\":1662,\"active_diff\":1626,\"deaths_diff\":36,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0272,\"last_update\":\"2021-10-01 04:21:35\",\"active\":840125,\"region\":{\"iso\":\"BRA\",\"province\":\"Goias\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.827\",\"long\":\"-49.8362\"},\"confirmed\":863618,\"deaths\":23493},{\"date\":\"2021-09-30\",\"confirmed_diff\":354,\"active_diff\":349,\"deaths_diff\":5,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0286,\"last_update\":\"2021-10-01 04:21:35\",\"active\":346062,\"region\":{\"iso\":\"BRA\",\"province\":\"Maranhao\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-4.9609\",\"long\":\"-45.2744\"},\"confirmed\":356236,\"deaths\":10174},{\"date\":\"2021-09-30\",\"confirmed_diff\":427,\"active_diff\":421,\"deaths_diff\":6,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0254,\"last_update\":\"2021-10-01 04:21:35\",\"active\":520328,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.6819\",\"long\":\"-56.9211\"},\"confirmed\":533891,\"deaths\":13563},{\"date\":\"2021-09-30\",\"confirmed_diff\":104,\"active_diff\":100,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0256,\"last_update\":\"2021-10-01 04:21:35\",\"active\":363362,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-20.7722\",\"long\":\"-54.7852\"},\"confirmed\":372917,\"deaths\":9555},{\"date\":\"2021-09-30\",\"confirmed_diff\":2414,\"active_diff\":2333,\"deaths_diff\":81,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0255,\"last_update\":\"2021-10-01 04:21:35\",\"active\":2085831,\"region\":{\"iso\":\"BRA\",\"province\":\"Minas Gerais\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-18.5122\",\"long\":\"-44.555\"},\"confirmed\":2140378,\"deaths\":54547},{\"date\":\"2021-09-30\",\"confirmed_diff\":297,\"active_diff\":292,\"deaths_diff\":5,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0282,\"last_update\":\"2021-10-01 04:21:35\",\"active\":574660,\"region\":{\"iso\":\"BRA\",\"province\":\"Para\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-1.9981\",\"long\":\"-54.9306\"},\"confirmed\":591318,\"deaths\":16658},{\"date\":\"2021-09-30\",\"confirmed_diff\":216,\"active_diff\":213,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-01 04:21:35\",\"active\":432363,\"region\":{\"iso\":\"BRA\",\"province\":\"Paraiba\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.24\",\"long\":\"-36.782\"},\"confirmed\":441674,\"deaths\":9311},{\"date\":\"2021-09-30\",\"confirmed_diff\":1807,\"active_diff\":1752,\"deaths_diff\":55,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0259,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1472413,\"region\":{\"iso\":\"BRA\",\"province\":\"Parana\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-25.2521\",\"long\":\"-52.0215\"},\"confirmed\":1511509,\"deaths\":39096},{\"date\":\"2021-09-30\",\"confirmed_diff\":549,\"active_diff\":533,\"deaths_diff\":16,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0318,\"last_update\":\"2021-10-01 04:21:35\",\"active\":600983,\"region\":{\"iso\":\"BRA\",\"province\":\"Pernambuco\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-8.8137\",\"long\":\"-36.9541\"},\"confirmed\":620723,\"deaths\":19740},{\"date\":\"2021-09-30\",\"confirmed_diff\":52,\"active_diff\":48,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.022,\"last_update\":\"2021-10-01 04:21:35\",\"active\":312323,\"region\":{\"iso\":\"BRA\",\"province\":\"Piaui\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.7183\",\"long\":\"-42.7289\"},\"confirmed\":319334,\"deaths\":7011},{\"date\":\"2021-09-30\",\"confirmed_diff\":119,\"active_diff\":118,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0199,\"last_update\":\"2021-10-01 04:21:35\",\"active\":361282,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Norte\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4026\",\"long\":\"-36.9541\"},\"confirmed\":368619,\"deaths\":7337},{\"date\":\"2021-09-30\",\"confirmed_diff\":1033,\"active_diff\":1000,\"deaths_diff\":33,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0243,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1402102,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-30.0346\",\"long\":\"-51.2177\"},\"confirmed\":1436962,\"deaths\":34860},{\"date\":\"2021-09-30\",\"confirmed_diff\":2277,\"active_diff\":2158,\"deaths_diff\":119,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0514,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1219597,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio de Janeiro\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-22.9068\",\"long\":\"-43.1729\"},\"confirmed\":1285731,\"deaths\":66134},{\"date\":\"2021-09-30\",\"confirmed_diff\":48,\"active_diff\":44,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0246,\"last_update\":\"2021-10-01 04:21:35\",\"active\":259350,\"region\":{\"iso\":\"BRA\",\"province\":\"Rondonia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-11.5057\",\"long\":\"-63.5806\"},\"confirmed\":265879,\"deaths\":6529},{\"date\":\"2021-09-30\",\"confirmed_diff\":13,\"active_diff\":10,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0159,\"last_update\":\"2021-10-01 04:21:35\",\"active\":124211,\"region\":{\"iso\":\"BRA\",\"province\":\"Roraima\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-2.7376\",\"long\":\"-62.0751\"},\"confirmed\":126212,\"deaths\":2001},{\"date\":\"2021-09-30\",\"confirmed_diff\":1121,\"active_diff\":1110,\"deaths_diff\":11,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0162,\"last_update\":\"2021-10-01 04:21:35\",\"active\":1173145,\"region\":{\"iso\":\"BRA\",\"province\":\"Santa Catarina\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-27.2423\",\"long\":\"-50.2189\"},\"confirmed\":1192421,\"deaths\":19276},{\"date\":\"2021-09-30\",\"confirmed_diff\":1550,\"active_diff\":1361,\"deaths_diff\":189,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0343,\"last_update\":\"2021-10-01 04:21:35\",\"active\":4216322,\"region\":{\"iso\":\"BRA\",\"province\":\"Sao Paulo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-23.5505\",\"long\":\"-46.6333\"},\"confirmed\":4366132,\"deaths\":149810},{\"date\":\"2021-09-30\",\"confirmed_diff\":24,\"active_diff\":23,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0216,\"last_update\":\"2021-10-01 04:21:35\",\"active\":272094,\"region\":{\"iso\":\"BRA\",\"province\":\"Sergipe\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.5741\",\"long\":\"-37.3857\"},\"confirmed\":278104,\"deaths\":6010},{\"date\":\"2021-09-30\",\"confirmed_diff\":195,\"active_diff\":192,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0169,\"last_update\":\"2021-10-01 04:21:35\",\"active\":220632,\"region\":{\"iso\":\"BRA\",\"province\":\"Tocantins\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.1753\",\"long\":\"-48.2982\"},\"confirmed\":224419,\"deaths\":3787}]}";
    private final String SPECIFIC_COUNTRY_WITH_END_DATE = "{\"data\":[{\"date\":\"2021-10-01\",\"confirmed_diff\":1,\"active_diff\":1,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0209,\"last_update\":\"2021-10-02 04:21:25\",\"active\":86087,\"region\":{\"iso\":\"BRA\",\"province\":\"Acre\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.0238\",\"long\":\"-70.812\"},\"confirmed\":87925,\"deaths\":1838},{\"date\":\"2021-10-01\",\"confirmed_diff\":39,\"active_diff\":35,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0261,\"last_update\":\"2021-10-02 04:21:25\",\"active\":232032,\"region\":{\"iso\":\"BRA\",\"province\":\"Alagoas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-9.5713\",\"long\":\"-36.782\"},\"confirmed\":238249,\"deaths\":6217},{\"date\":\"2021-10-01\",\"confirmed_diff\":22,\"active_diff\":19,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0161,\"last_update\":\"2021-10-02 04:21:25\",\"active\":120874,\"region\":{\"iso\":\"BRA\",\"province\":\"Amapa\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"0.902\",\"long\":\"-52.003\"},\"confirmed\":122858,\"deaths\":1984},{\"date\":\"2021-10-01\",\"confirmed_diff\":51,\"active_diff\":50,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0322,\"last_update\":\"2021-10-02 04:21:25\",\"active\":412803,\"region\":{\"iso\":\"BRA\",\"province\":\"Amazonas\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-3.4168\",\"long\":\"-65.8561\"},\"confirmed\":426527,\"deaths\":13724},{\"date\":\"2021-10-01\",\"confirmed_diff\":571,\"active_diff\":564,\"deaths_diff\":7,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0218,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1207504,\"region\":{\"iso\":\"BRA\",\"province\":\"Bahia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.5797\",\"long\":\"-41.7007\"},\"confirmed\":1234370,\"deaths\":26866},{\"date\":\"2021-10-01\",\"confirmed_diff\":293,\"active_diff\":281,\"deaths_diff\":12,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0258,\"last_update\":\"2021-10-02 04:21:25\",\"active\":916523,\"region\":{\"iso\":\"BRA\",\"province\":\"Ceara\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4984\",\"long\":\"-39.3206\"},\"confirmed\":940756,\"deaths\":24233},{\"date\":\"2021-10-01\",\"confirmed_diff\":937,\"active_diff\":921,\"deaths_diff\":16,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-02 04:21:25\",\"active\":485706,\"region\":{\"iso\":\"BRA\",\"province\":\"Distrito Federal\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.7998\",\"long\":\"-47.8645\"},\"confirmed\":496186,\"deaths\":10480},{\"date\":\"2021-10-01\",\"confirmed_diff\":963,\"active_diff\":960,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0214,\"last_update\":\"2021-10-02 04:21:25\",\"active\":574441,\"region\":{\"iso\":\"BRA\",\"province\":\"Espirito Santo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-19.1834\",\"long\":\"-40.3089\"},\"confirmed\":587002,\"deaths\":12561},{\"date\":\"2021-10-01\",\"confirmed_diff\":1541,\"active_diff\":1501,\"deaths_diff\":40,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0272,\"last_update\":\"2021-10-02 04:21:25\",\"active\":841626,\"region\":{\"iso\":\"BRA\",\"province\":\"Goias\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-15.827\",\"long\":\"-49.8362\"},\"confirmed\":865159,\"deaths\":23533},{\"date\":\"2021-10-01\",\"confirmed_diff\":326,\"active_diff\":323,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0285,\"last_update\":\"2021-10-02 04:21:25\",\"active\":346385,\"region\":{\"iso\":\"BRA\",\"province\":\"Maranhao\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-4.9609\",\"long\":\"-45.2744\"},\"confirmed\":356562,\"deaths\":10177},{\"date\":\"2021-10-01\",\"confirmed_diff\":445,\"active_diff\":440,\"deaths_diff\":5,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0254,\"last_update\":\"2021-10-02 04:21:25\",\"active\":520768,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-12.6819\",\"long\":\"-56.9211\"},\"confirmed\":534336,\"deaths\":13568},{\"date\":\"2021-10-01\",\"confirmed_diff\":167,\"active_diff\":158,\"deaths_diff\":9,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0256,\"last_update\":\"2021-10-02 04:21:25\",\"active\":363520,\"region\":{\"iso\":\"BRA\",\"province\":\"Mato Grosso do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-20.7722\",\"long\":\"-54.7852\"},\"confirmed\":373084,\"deaths\":9564},{\"date\":\"2021-10-01\",\"confirmed_diff\":2856,\"active_diff\":2790,\"deaths_diff\":66,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0255,\"last_update\":\"2021-10-02 04:21:25\",\"active\":2088621,\"region\":{\"iso\":\"BRA\",\"province\":\"Minas Gerais\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-18.5122\",\"long\":\"-44.555\"},\"confirmed\":2143234,\"deaths\":54613},{\"date\":\"2021-10-01\",\"confirmed_diff\":256,\"active_diff\":254,\"deaths_diff\":2,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0282,\"last_update\":\"2021-10-02 04:21:25\",\"active\":574914,\"region\":{\"iso\":\"BRA\",\"province\":\"Para\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-1.9981\",\"long\":\"-54.9306\"},\"confirmed\":591574,\"deaths\":16660},{\"date\":\"2021-10-01\",\"confirmed_diff\":163,\"active_diff\":162,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0211,\"last_update\":\"2021-10-02 04:21:25\",\"active\":432525,\"region\":{\"iso\":\"BRA\",\"province\":\"Paraiba\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.24\",\"long\":\"-36.782\"},\"confirmed\":441837,\"deaths\":9312},{\"date\":\"2021-10-01\",\"confirmed_diff\":1792,\"active_diff\":1779,\"deaths_diff\":13,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0258,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1474192,\"region\":{\"iso\":\"BRA\",\"province\":\"Parana\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-25.2521\",\"long\":\"-52.0215\"},\"confirmed\":1513301,\"deaths\":39109},{\"date\":\"2021-10-01\",\"confirmed_diff\":469,\"active_diff\":462,\"deaths_diff\":7,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0318,\"last_update\":\"2021-10-02 04:21:25\",\"active\":601445,\"region\":{\"iso\":\"BRA\",\"province\":\"Pernambuco\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-8.8137\",\"long\":\"-36.9541\"},\"confirmed\":621192,\"deaths\":19747},{\"date\":\"2021-10-01\",\"confirmed_diff\":76,\"active_diff\":75,\"deaths_diff\":1,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.022,\"last_update\":\"2021-10-02 04:21:25\",\"active\":312398,\"region\":{\"iso\":\"BRA\",\"province\":\"Piaui\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-7.7183\",\"long\":\"-42.7289\"},\"confirmed\":319410,\"deaths\":7012},{\"date\":\"2021-10-01\",\"confirmed_diff\":97,\"active_diff\":94,\"deaths_diff\":3,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0199,\"last_update\":\"2021-10-02 04:21:25\",\"active\":361376,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Norte\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-5.4026\",\"long\":\"-36.9541\"},\"confirmed\":368716,\"deaths\":7340},{\"date\":\"2021-10-01\",\"confirmed_diff\":1916,\"active_diff\":1895,\"deaths_diff\":21,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0242,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1403997,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio Grande do Sul\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-30.0346\",\"long\":\"-51.2177\"},\"confirmed\":1438878,\"deaths\":34881},{\"date\":\"2021-10-01\",\"confirmed_diff\":2745,\"active_diff\":2618,\"deaths_diff\":127,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0514,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1222215,\"region\":{\"iso\":\"BRA\",\"province\":\"Rio de Janeiro\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-22.9068\",\"long\":\"-43.1729\"},\"confirmed\":1288476,\"deaths\":66261},{\"date\":\"2021-10-01\",\"confirmed_diff\":0,\"active_diff\":0,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0246,\"last_update\":\"2021-10-02 04:21:25\",\"active\":259350,\"region\":{\"iso\":\"BRA\",\"province\":\"Rondonia\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-11.5057\",\"long\":\"-63.5806\"},\"confirmed\":265879,\"deaths\":6529},{\"date\":\"2021-10-01\",\"confirmed_diff\":8,\"active_diff\":8,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0159,\"last_update\":\"2021-10-02 04:21:25\",\"active\":124219,\"region\":{\"iso\":\"BRA\",\"province\":\"Roraima\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-2.7376\",\"long\":\"-62.0751\"},\"confirmed\":126220,\"deaths\":2001},{\"date\":\"2021-10-01\",\"confirmed_diff\":960,\"active_diff\":945,\"deaths_diff\":15,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0162,\"last_update\":\"2021-10-02 04:21:25\",\"active\":1174090,\"region\":{\"iso\":\"BRA\",\"province\":\"Santa Catarina\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-27.2423\",\"long\":\"-50.2189\"},\"confirmed\":1193381,\"deaths\":19291},{\"date\":\"2021-10-01\",\"confirmed_diff\":1616,\"active_diff\":1473,\"deaths_diff\":143,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0343,\"last_update\":\"2021-10-02 04:21:25\",\"active\":4217795,\"region\":{\"iso\":\"BRA\",\"province\":\"Sao Paulo\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-23.5505\",\"long\":\"-46.6333\"},\"confirmed\":4367748,\"deaths\":149953},{\"date\":\"2021-10-01\",\"confirmed_diff\":30,\"active_diff\":30,\"deaths_diff\":0,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0216,\"last_update\":\"2021-10-02 04:21:25\",\"active\":272124,\"region\":{\"iso\":\"BRA\",\"province\":\"Sergipe\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.5741\",\"long\":\"-37.3857\"},\"confirmed\":278134,\"deaths\":6010},{\"date\":\"2021-10-01\",\"confirmed_diff\":238,\"active_diff\":234,\"deaths_diff\":4,\"recovered\":0,\"recovered_diff\":0,\"fatality_rate\":0.0169,\"last_update\":\"2021-10-02 04:21:25\",\"active\":220866,\"region\":{\"iso\":\"BRA\",\"province\":\"Tocantins\",\"cities\":[],\"name\":\"Brazil\",\"lat\":\"-10.1753\",\"long\":\"-48.2982\"},\"confirmed\":224657,\"deaths\":3791}]}";

    private WeakConcurrentHashMap<String, JSONObject> cache;
    private final String NOT_FOUND = "{\"data\":[]}";

    private JSONMethods jsonMeth = new JSONMethods();

    @BeforeEach
    void setUp() throws IOException, InterruptedException, ParseException {
        client = mock(Client.class);
        
        api.setClient(client);
        cache = api.getConcurrentCash();
    }

    @Test
    void getAllCountries() throws IOException, InterruptedException, ParseException {
        
        when(client.doRequest(REGIONS_URL, cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(ALL_COUNTRIES));

        JSONObject response = api.getAllCountries();
        JSONArray data = (JSONArray) response.get("data");

        assertThat(data.size(), is(14));
        assertThat(((JSONObject) data.get(0)).get("name"), is("China"));
    }

    @Test
    void getSpecificCountry() throws IOException, InterruptedException, ParseException {
        when(client.doRequest(REPORTS_URL + "?region_name=brazil", cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(SPECIFIC_COUNTRY));

        DataOutput data = api.getCountryByRegion("brazil").get(0);
       
        assertThat(data.getCountry(), is("Brazil"));
        assertThat(data.getDate(), is(LocalDate.parse("2022-04-16")));
    }

    @Test
    void getSpecificCountryWithDate() throws IOException, InterruptedException, ParseException {
        when(client.doRequest(REPORTS_URL + "?region_name=brazil&date=2021-09-30", cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(SPECIFIC_COUNTRY_WITH_START_DATE));

        when(client.doRequest(REPORTS_URL + "?region_name=brazil&date=2021-10-01", cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(SPECIFIC_COUNTRY_WITH_END_DATE));

        List<DataOutput> response = api.getCountryByRegionAndDate("brazil","2021-09-30","2021-10-01");

        assertThat(response.get(0).getCountry(), is("Brazil"));
        assertThat(response.get(1).getCountry(), is("Brazil"));
        assertThat(response.get(0).getDate(), is(LocalDate.parse("2021-09-30")));
        assertThat(response.get(1).getDate(), is(LocalDate.parse("2021-10-01")));

    }

    @Test
    void getSpecificCountryNotFound() throws IOException, InterruptedException, ParseException {
        when(client.doRequest(REPORTS_URL + "?region_name=not_existent", cache, api.getHost(), api.getKey()))
        .thenReturn(jsonMeth.generateJSONObject(NOT_FOUND));

        List<DataOutput> response = api.getCountryByRegion("not_existent");
       
        assertTrue(response.isEmpty());
    }

}
