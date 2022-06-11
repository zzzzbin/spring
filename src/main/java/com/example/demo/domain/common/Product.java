package com.example.demo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product {
    private String name;
    private double price;

    @Override
    public String toString() {
        return name + " " + price;
    }
}
