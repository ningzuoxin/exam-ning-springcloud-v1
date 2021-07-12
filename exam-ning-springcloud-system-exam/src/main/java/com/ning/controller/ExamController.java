package com.ning.controller;

import com.ning.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @GetMapping(value = {"/", "/index"})
    public Result<String> index() {
        System.out.println(" ========== exam index ========== ");
        return Result.ok("exam index");
    }

}
