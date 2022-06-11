package com.example.demo.domain.common;

import lombok.Data;

@Data
public class Battery extends Product{
    private boolean rechargeable;

    public Battery() {
        super();
    }

    public Battery(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return super.toString() +" "+ rechargeable;
    }
}
