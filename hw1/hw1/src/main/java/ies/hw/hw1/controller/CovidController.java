package ies.hw.hw1.controller;

import java.text.MessageFormat;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.hw.hw1.http.API1;
import ies.hw.hw1.http.API2;
import ies.hw.hw1.models.Cache;
import ies.hw.hw1.models.DataOutput;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CovidController {

    private static final Logger logger = LogManager.getLogger(CovidController.class);
    
    @Autowired
    private API1 client1;

    @Autowired
    private API2 client2;

    @Operation(summary = "API 1 - Get all countries")
    @ApiResponse(responseCode = "200", description = "Got all countries")
    @GetMapping("/api1/countries")
    public JSONObject getAllCountries() throws ParseException, InterruptedException {
        
        if (logger.isInfoEnabled())
            logger.info("API 1 - Getting all countries");
        return client1.getAllCountries();
    }

    @Operation(summary = "API 1 - Get specific country")
    @ApiResponse(responseCode = "200", description = "Got specific country")
    @GetMapping("/api1/countries/{name}")
    public List<DataOutput> getCountryByName(@PathVariable(value = "name") String name, 
    @RequestParam(required = false) String startDay,
    @RequestParam(required = false) String endDay
    ) throws ParseException, InterruptedException {

        if (startDay != null && endDay != null) {

            if (logger.isInfoEnabled())
                logger.info(MessageFormat.format("API 1 - Getting Country {0} for day: {1} until {2}", name, startDay, endDay));

            return client1.getCountryByRegionAndDate(name, startDay, endDay);
        }

        if (logger.isInfoEnabled())
            logger.info(MessageFormat.format("API 1 - Getting Country by name: {0}", name));
        return client1.getCountryByRegion(name);
    }

    @Operation(summary = "API 1 - Get cache usage")
    @ApiResponse(responseCode = "200", description = "Got cache usage")
    @GetMapping("/api1/cache/usage")
    public Cache getCache1() {

        if (logger.isInfoEnabled())
            logger.info("API 1 - Returning cache info: ");
        return client1.getCacheInfo(); 
    }

    @Operation(summary = "API 2 - Get all countries")
    @ApiResponse(responseCode = "200", description = "Got all countries")
    @GetMapping("/api2/countries")
    public JSONObject getAllCountries2() throws ParseException, InterruptedException {

        if (logger.isInfoEnabled())
            logger.info("API 2 - Getting all countries");
        return client2.getAllCountries();
    }

    @Operation(summary = "API 2 - Get specific country")
    @ApiResponse(responseCode = "200", description = "Got specific country")
    @GetMapping("/api2/countries/{name}")
    public List<DataOutput> getCountryByName2(@PathVariable(value = "name") String name, 
    @RequestParam(required = false) String startDay, 
    @RequestParam(required = false) String endDay) throws ParseException, InterruptedException {

        if (startDay != null && endDay != null) {
            if (logger.isInfoEnabled())
                logger.info(MessageFormat.format("API 2 - Getting Country {0} for day: {1} until {2}", name, startDay, endDay));
            return client2.getCountryByRegionAndDate(name, startDay, endDay);
        }

        if (logger.isInfoEnabled())
            logger.info(MessageFormat.format("API 2 - Getting Country by name: {0}", name));
        return client2.getCountryByRegion(name);
    }

    @Operation(summary = "API 2 - Get cache usage")
    @ApiResponse(responseCode = "200", description = "Got cache usage")
    @GetMapping("/api2/cache/usage")
    public Cache getCache2() {

        if (logger.isInfoEnabled())
            logger.info("API 2 - Returning cache info: ");
        return client2.getCacheInfo(); 
    }

}