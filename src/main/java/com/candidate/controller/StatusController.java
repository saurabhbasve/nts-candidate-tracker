package com.candidate.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/")
    public String home(){
        return " Application is Running ";
    }
}
