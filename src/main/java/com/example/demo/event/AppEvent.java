package com.example.demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AppEvent<T> extends ApplicationEvent {
    private T eventData;

    public AppEvent(T source) {
        super(source);
        this.eventData = source;
    }
}
