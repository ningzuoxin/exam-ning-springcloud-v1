package com.ning.controller;

import com.ning.api.user.RemoteUserService;
import com.ning.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;

    @Autowired
    RemoteUserService remoteUserService;

    @GetMapping(value = {"/", "/index"})
    public Result<String> index() {
        System.out.println(" ========== index ========== " + port);
        return remoteUserService.index();
    }

}
