package com.example.demo.rest;

import com.example.demo.dto.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/greeting")
@Slf4j
public class GreetingController {

    private final Greeting greeting;

    public GreetingController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/test")
    public DeferredResult<String> getTestRequest() {
        log.info("Request processing started");

        final DeferredResult<String> deferredResult = new DeferredResult<>();

        setResultInOtherThread(deferredResult);

        log.info("Request processing finished");

        return deferredResult;
    }

    private void setResultInOtherThread(DeferredResult<String> deferredResult) {
        new Thread(()->{
            log.info("Deferred task started");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("Deferred task finished");

            deferredResult.setResult("Test deferred result");
        }).start();
    }

    @GetMapping
    String getGreeting() {
        return greeting.getName();
    }

    @GetMapping("coffee")
    String getNameAndCoffee(){
        return greeting.getCoffee();
    }
}
