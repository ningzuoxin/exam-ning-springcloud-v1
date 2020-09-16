package com.ning.controller;

import com.ning.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/index")
    public Result<String> index() {
        return Result.ok(" ====== system user index ======= " + port);
    }
}
