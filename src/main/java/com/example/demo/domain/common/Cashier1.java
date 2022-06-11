package com.example.demo.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Date;

@Component
public class Cashier1 implements ApplicationEventPublisherAware {
    @Value("checkout")
    private String fileName;

    @Value("/Users/trung/Learn/Projects/Spring")
    private String path;
    private BufferedWriter writer;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @PostConstruct
    public void openFile() throws IOException {
        File targetDir = new File(path);
        System.out.println(targetDir);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists()) {
            checkoutFile.createNewFile();
        }
        System.out.println(checkoutFile.getAbsolutePath());
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile, true)));
    }

    public void checkout(ShoppingCart cart) throws IOException {
//        writer.write(new Date() + "\t" + cart.getItems() + "\r\n");
        writer.flush();
        System.out.println("Open file");
        CheckoutEvent checkoutEvent = new CheckoutEvent(cart, new Date());
        applicationEventPublisher.publishEvent(checkoutEvent);
    }

    @PreDestroy
    public void closeFile() throws IOException {
        System.out.println("Close file");
        writer.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
