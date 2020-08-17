package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
public class PoseidonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }

}
