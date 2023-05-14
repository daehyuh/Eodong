package com.example.eodong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EodongApplication {
    public static void main(String[] args) {
        System.out.println("hello");
        SpringApplication.run(EodongApplication.class, args);
    }

}