package com.example.demo.controller;

import com.example.demo.event.AppEventPublisher;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @Autowired
    AppEventPublisher appEventPublisher;

    @GetMapping("/send")
    public String sendMessage(String mobile) {
        notificationService.sendTextMessage(mobile);
        return "message sent successfully!!!" + Thread.currentThread().getName();
    }

    @GetMapping("/publish/{message}")
    public String publish(@PathVariable String message){
        //DB call
        appEventPublisher.publishEvent("Published: " +message);
        return "Events sent successfully";
    }
}
