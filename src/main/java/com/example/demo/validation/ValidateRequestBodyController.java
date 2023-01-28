package com.example.demo.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
public class ValidateRequestBodyController {
    @PostMapping("/validateBody")
    ResponseEntity<String> validateBoby(@Valid @RequestBody Input input){
        return ResponseEntity.ok("valid");
    }
}
