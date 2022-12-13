package com.example.consolapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @GetMapping("/test")
    public String getData() {
        return "CONSOLE-SERVICE data from CONSOLE microservice";
    }
}
