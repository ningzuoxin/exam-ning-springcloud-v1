package com.ning.controller;

import com.ning.model.Result;
import com.ning.service.ExamResultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/exam/result/")
@RestController
public class ExamResultController {

    @Resource
    ExamResultService examResultService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询考试结果列表")
    public Result list(@RequestParam(value = "id", defaultValue = "1") @ApiParam(name = "id", example = "1") Integer id,
                       @RequestParam(value = "userId", defaultValue = "1") @ApiParam(name = "userId", example = "1") Integer userId) {
        return examResultService.list(id, userId);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "查询考试结果详情")
    public Result detail(@RequestParam(value = "id", defaultValue = "1") @ApiParam(name = "id", example = "1") Integer id) {
        return examResultService.detail(id);
    }

}
