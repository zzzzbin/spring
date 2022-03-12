package com.example.demo.jsonpath;

import com.example.demo.dto.Country;
import com.example.demo.dto.CountryResponse;
import com.example.demo.dto.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryInvoker {
    public CountryResponse getCountryInfo() {
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setDescription("What is description");
        countryResponse.setRegion("Asia");
        List<Country> countryList = new ArrayList<>();

        Country country1 = new Country();
        country1.setCountry("Singapore");
        Data data1 = new Data("Don't know", 3, 34, "SGN");
        country1.setData(data1);
        countryList.add(country1);

        Country country2 = new Country();
        country2.setCountry("Cambodia");
        Data data2 = new Data("Jakarta", 3, 34, "CBN");
        country2.setData(data2);
        countryList.add(country2);

        countryResponse.setCountries(countryList);
        return countryResponse;
    }
}
