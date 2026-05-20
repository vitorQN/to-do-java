package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ToDoController {
    @GetMapping("/")
    public String hello() {
        return "My Spring Boot App Works!";
    }
}
