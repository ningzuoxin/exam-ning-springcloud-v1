package com.ning.controller;

import com.ning.common.enums.ExamQuestionTypeEnum;
import com.ning.model.Result;
import com.ning.service.ExamQuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/exam/question/")
@RestController
public class ExamQuestionController {

    @Resource
    ExamQuestionService examQuestionService;

    @GetMapping(value = "/type")
    @ApiOperation(value = "查询题型")
    public Result<List<Map<String, String>>> types() {
        return Result.ok(ExamQuestionTypeEnum.listExamQuestionTypes());
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询考题列表")
    public Result selectPage(@RequestParam(value = "type", required = false) @ApiParam(name = "type", example = "") String type,
                             @RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                             @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                             @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return examQuestionService.selectExamQuestionPage(type, keyword, pNum, pSize);
    }

}
