package com.ning.controller;

import com.ning.api.user.RemoteUserIndexService;
import com.ning.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;

    @Autowired
    RemoteUserIndexService remoteUserIndexService;

    @GetMapping(value = {"/", "/index"})
    public Result<String> index() {
        System.out.println(" ========== index ========== " + port);
        return remoteUserIndexService.index();
    }

    @GetMapping(value = "/map")
    public Result<Map<String, Object>> map() {
        return remoteUserIndexService.map();
    }

}
