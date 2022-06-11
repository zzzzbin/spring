package com.example.demo.domain.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;

@Configuration
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {
    @Value("${endofyear.discount:0.0}")
    private Double specialEndofyearDiscountField;

    @Bean
    public Product aaa() {
        Battery p1 = new Battery();
        p1.setName("AAA");
        p1.setPrice(2.5);
        p1.setRechargeable(true);
        return p1;
    }

    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", specialEndofyearDiscountField);
        p2.setCapacity(700);
        return p2;
    }

    @Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DVD-RW", 3.0);
        p2.setCapacity(700);
        return p2;
    }

    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {
        String path = System.getProperty("java.io.tmpdir") + "/cashier";
        Cashier c1 = new Cashier();
        c1.setFileName("checkout");
        c1.setPath(path);
        return c1;
    }

    @Bean
    public Product aaa1() {
        return ProductCreator.createProduct("aaa");
    }

    @Bean
    public Product cdrw1() {
        return ProductCreator.createProduct("cdrw");
    }

    @Bean
    public Product dvdrw1() {
        return ProductCreator.createProduct("dvdrw");
    }

    @Bean
    public ProductCreator productCreatorFactory() {
        ProductCreator factory = new ProductCreator();
        HashMap<String, Product> products = new HashMap<>();
        products.put("aaa", new Battery("AAA", 2.5));
        products.put("cdrw", new Disc("CD-RW", 1.5));
        products.put("dvdrw", new Disc("DVD-RW", 3.0));
        factory.setProducts(products);
        return factory;
    }

    @Bean
    public Product aaa2() {
        return productCreatorFactory().createProduct("aaa");
    }

}
