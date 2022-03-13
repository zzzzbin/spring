package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    @Async
    public void sendTextMessage(String mobile) {
        sleep(5);
        System.out.println("Call 3rd party to send text message"+ Thread.currentThread().getName());
        System.out.println(10/0);
    }

    private void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
