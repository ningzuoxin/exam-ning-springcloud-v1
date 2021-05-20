package com.ning.controller;

import com.ning.common.enums.ExamTestPaperTypeEnum;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/exam/testPaper/")
@RestController
public class ExamTestPaperController {

    @GetMapping(value = "/type")
    @ApiOperation(value = "查询试卷类型")
    public Result<List<Map<String, String>>> types() {
        return Result.ok(ExamTestPaperTypeEnum.listExamTestPaperTypes());
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加试卷")
    public Result add(@RequestBody @Valid ExamTestPaperModel examTestPaperModel) {
        return Result.ok();
    }

}
