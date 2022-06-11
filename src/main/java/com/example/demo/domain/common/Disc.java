package com.example.demo.domain.common;

import lombok.Data;

@Data
public class Disc extends Product {
    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return super.toString() +" "+ capacity;
    }
}
