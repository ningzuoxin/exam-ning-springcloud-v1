package com.ning.controller;

import com.ning.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/index")
    public Result<String> index() {
        return Result.ok(" ====== system user index ======= " + port);
    }

    @GetMapping(value = "/map")
    public Result<Map<String, Object>> map() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三丰");
        map.put("age", "110");
        map.put("port", port);
        return Result.ok(map);
    }

}
