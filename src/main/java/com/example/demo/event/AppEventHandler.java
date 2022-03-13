package com.example.demo.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AppEventHandler {
    @EventListener
    @Async
    public void handleEvent(AppEvent<LogData> appEvent) throws JsonProcessingException {
        //3rd api call

        System.out.println("appEvent = " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(appEvent.getEventData()));
    }
}
