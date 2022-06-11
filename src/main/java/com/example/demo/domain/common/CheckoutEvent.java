package com.example.demo.domain.common;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class CheckoutEvent {
    private final ShoppingCart cart;
    private final Date time;

    public CheckoutEvent(ShoppingCart cart, Date time) {
        this.cart = cart;
        this.time = time;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Date getTime() {
        return time;
    }
}
