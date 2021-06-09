package com.ning.controller;

import com.ning.common.model.MarkScoreModel;
import com.ning.model.Result;
import com.ning.service.ExamResultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/marks")
    @ApiOperation(value = "查询待批阅试卷列表")
    public Result marks(@RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                        @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return examResultService.selectPage(pNum, pSize);
    }

    @PostMapping(value = "/doMark")
    @ApiOperation(value = "打分")
    public Result doMark(@RequestBody MarkScoreModel markScoreModel) {
        return examResultService.doMark(markScoreModel);
    }

}
