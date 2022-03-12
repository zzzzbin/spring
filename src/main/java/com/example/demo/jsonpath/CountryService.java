package com.example.demo.jsonpath;

import com.example.demo.dto.CountryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    private CountryInvoker countryInvoker;

    ObjectMapper mapper = new ObjectMapper();

    public Object getFormattedResponse(String jsonExpr, Class<?> classType) throws JsonProcessingException {
        CountryResponse response = countryInvoker.getCountryInfo();
        String jsonRes = mapper.writeValueAsString(response);
        Object countryRes = JsonPath.parse(jsonRes).read(jsonExpr, classType);
        return countryRes;
    }
}