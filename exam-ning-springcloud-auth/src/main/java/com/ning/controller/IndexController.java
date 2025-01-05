package com.ning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class IndexController {

    @GetMapping(value = "/index")
    public String index() {
        return Instant.now().toString();
    }

}
