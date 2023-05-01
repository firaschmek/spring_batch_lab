package com.firas.spring.batch.lab;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableBatchProcessing
@ComponentScan({"com.firas.spring.batch.config","com.firas.spring.batch.service","com.firas.spring.batch.listener"})
public class SpringBatchTutorielApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchTutorielApplication.class, args);
    }

}
