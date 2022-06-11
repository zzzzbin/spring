package com.example.demo.domain.common;

import com.example.demo.dto.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class CheckoutEvent extends ApplicationEvent {
    private final ShoppingCart cart;
    private final Date time;

    public CheckoutEvent(ShoppingCart cart, Date time) {
        super(cart);
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
