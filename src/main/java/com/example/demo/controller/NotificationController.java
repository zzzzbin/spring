package com.example.demo.controller;

import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/send")
    public String sendMessage(String mobile) {
        notificationService.sendTextMessage(mobile);
        return "message sent successfully!!!" + Thread.currentThread().getName();
    }
}
