package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class in charge of launching the Poseidon application.
 */
@SpringBootApplication
public class PoseidonApplication {

    public static void main(String[] args) {

        SpringApplication.run(PoseidonApplication.class, args);

    }
}
