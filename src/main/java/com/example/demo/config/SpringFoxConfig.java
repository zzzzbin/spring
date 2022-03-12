package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket booksApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Trung Nguyen").apiInfo(apiInfo()).select()
                .paths(regex("/book.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("My Book Service")
                .description("This is my sample of SWAGGER 2 for book api")
                .termsOfServiceUrl("This is term of service url here")
                .license("Free for everyone")
                .licenseUrl("This is url for licence")
                .version("1.0").build();
    }
}
