package com.example.demo.domain.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceGeneratorConfiguration {
    @Bean
    public SequenceGenerator sequenceGenerator(){
        SequenceGenerator sq = new SequenceGenerator();
        sq.setPrefix("30");
        sq.setSuffix("A");
        sq.setInitial(100000);
        return sq;
    }
}
