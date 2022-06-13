package com.pccw.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PccwRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PccwRegisterApplication.class, args);
    }

}
