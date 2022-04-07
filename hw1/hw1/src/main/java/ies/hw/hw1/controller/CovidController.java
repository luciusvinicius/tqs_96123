package ies.hw.hw1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.hw.hw1.service.CovidService;

@RestController
public class CovidController {
    

    @Autowired
    private CovidService service;

    @GetMapping("/stats")
    public String getStats() {
        return "sus";
    }
}
