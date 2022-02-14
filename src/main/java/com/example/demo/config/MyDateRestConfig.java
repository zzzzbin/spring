package com.example.demo.config;

import com.example.demo.domain.ecommerce.MProduct;
import com.example.demo.domain.ecommerce.ProductCategory;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class MyDateRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportHttpMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        config.getExposureConfiguration()
                .forDomainType(MProduct.class)
                .withItemExposure((metdata, httpMethods1) -> httpMethods1.disable(unsupportHttpMethods))
                .withCollectionExposure((metdata, httpMethods1) -> httpMethods1.disable(unsupportHttpMethods));
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods1) -> httpMethods1.disable(unsupportHttpMethods))
                .withCollectionExposure((metdata, httpMethods1) -> httpMethods1.disable(unsupportHttpMethods));
    }
}
