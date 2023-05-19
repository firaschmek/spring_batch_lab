package com.firas.spring.batch.lab;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.firas.spring.batch.config","com.firas.spring.batch.service"
        ,"com.firas.spring.batch.listener","com.firas.spring.batch.reader","com.firas.spring.batch.processor","com.firas.spring.batch.writer"})
public class SpringBatchTutorielApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchTutorielApplication.class, args);
    }

}
