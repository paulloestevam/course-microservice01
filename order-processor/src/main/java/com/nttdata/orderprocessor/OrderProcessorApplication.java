package com.nttdata.orderprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OrderProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }

}
