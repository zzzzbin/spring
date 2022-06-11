package com.example.demo;

import com.example.demo.domain.common.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo.domain.common");
//        SequenceGenerator bean = context.getBean(SequenceGenerator.class);
//        System.out.println(bean.getSequence());
//        System.out.println(bean.getSequence());
//
//        SequenceDao sequenceDao = context.getBean(SequenceDao.class);
//
//        System.out.println(sequenceDao.getNextValue("IT"));
//        System.out.println(sequenceDao.getNextValue("IT"));
//
//        SequenceService sequenceService = context.getBean(SequenceService.class);
//        System.out.println(sequenceService.generate("IT"));

        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        Product dvdrw = context.getBean("dvdrw", Product.class);
        ShoppingCart shoppingCart = context.getBean("shoppingCart", ShoppingCart.class);
        shoppingCart.addItem(aaa);
        shoppingCart.addItem(cdrw);
        System.out.println("Shopping cart 1 contains " + shoppingCart.getItems());

        ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart 2 contains " + cart2.getItems());

    }
}
