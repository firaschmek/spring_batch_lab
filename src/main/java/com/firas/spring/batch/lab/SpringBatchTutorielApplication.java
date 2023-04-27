package com.firas.spring.batch.lab;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchTutorielApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchTutorielApplication.class, args);
    }

}
