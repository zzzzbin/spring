package com.example.demo.jsonpath;

import com.example.demo.dto.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/getCountryNames")
    public List<String> getCountryNames() throws JsonProcessingException {
        String expression = "$.countries[*].country";
        return (List<String>) countryService.getFormattedResponse( expression, List.class);
    }

    @GetMapping("/getCountries")
    public List<Country> getCountries() throws JsonProcessingException {
        String expression = "$.countries[*]";
        return (List<Country>) countryService.getFormattedResponse( expression, List.class);
    }
}
