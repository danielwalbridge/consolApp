package com.example.consolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;




@SpringBootApplication
@EnableDiscoveryClient
public class ConsoleAppApplication{

    public static void main(String[] args) {
        SpringApplication.run(ConsoleAppApplication.class, args);
    }
}
