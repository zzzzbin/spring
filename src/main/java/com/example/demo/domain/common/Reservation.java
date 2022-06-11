package com.example.demo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class Reservation {
    private String courtName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int hour;
    private Player player;
    private SportType sportType;
}
