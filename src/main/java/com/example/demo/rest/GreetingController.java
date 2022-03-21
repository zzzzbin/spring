package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Value("${greeting-name: Default}")
    private String name;

    @Value("${greeting-coffee: ${greeting-name} is drinking Trung nguyen cafe}")
    private String coffee;

    @GetMapping
    String getGreeting() {
        return name;
    }

    @GetMapping("coffee")
    String getNameAndCoffee(){
        return coffee;
    }
}
