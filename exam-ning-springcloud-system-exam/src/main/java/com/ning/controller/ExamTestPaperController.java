package com.ning.controller;

import cn.hutool.core.date.DateUtil;
import com.ning.common.enums.ExamTestPaperTypeEnum;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.model.Result;
import com.ning.service.ExamTestPaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/exam/testPaper/")
@RestController
public class ExamTestPaperController {

    @Resource
    ExamTestPaperService examTestPaperService;

    @GetMapping(value = "/type")
    @ApiOperation(value = "查询试卷类型")
    public Result<List<Map<String, String>>> types() {
        return Result.ok(ExamTestPaperTypeEnum.listExamTestPaperTypes());
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加试卷")
    public Result add(@RequestBody @Valid ExamTestPaperModel examTestPaperModel) {
        int now = (int) DateUtil.currentSeconds();
        examTestPaperModel.setIsDelete(0);
        examTestPaperModel.setCreateTime(now);
        examTestPaperModel.setUpdateTime(now);
        return examTestPaperService.add(examTestPaperModel);
    }

}
