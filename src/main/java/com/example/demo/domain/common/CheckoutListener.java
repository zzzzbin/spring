package com.example.demo.domain.common;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener {
    @EventListener
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.println("Checkout event newer [" + event.getTime() + "]");
    }
}
