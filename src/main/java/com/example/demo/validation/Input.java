package com.example.demo.validation;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class Input {
    private Long id;

    @Min(1)
    @Max(10)
    private int numberBetweenOneAndTen;

    // Note that this is actually not a valid IP address pattern, since
    // it allows values greater than 255 per octet.
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ipAddress;
}
