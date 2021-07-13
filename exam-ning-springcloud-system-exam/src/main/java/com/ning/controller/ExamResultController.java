package com.ning.controller;

import com.ning.common.model.MarkScoreModel;
import com.ning.model.Result;
import com.ning.service.ExamResultService;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/result/")
@RestController
public class ExamResultController {

    @Resource
    ExamResultService examResultService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询考试结果列表")
    public Result list(@RequestParam(value = "id", defaultValue = "1") @ApiParam(name = "id", example = "1") Integer id) {
        return examResultService.list(id, SecurityUtils.getLoginUser().getUserId().intValue());
    }

    @PreAuthorize("@ss.hasPermi('exam:result:detail')")
    @GetMapping(value = "/detail")
    @ApiOperation(value = "查询考试结果详情")
    public Result detail(@RequestParam(value = "id", defaultValue = "1") @ApiParam(name = "id", example = "1") Integer id) {
        return examResultService.detail(id);
    }

    @PreAuthorize("@ss.hasPermi('exam:result:marks')")
    @GetMapping(value = "/marks")
    @ApiOperation(value = "查询待批阅试卷列表")
    public Result marks(@RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                        @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return examResultService.selectPage(pNum, pSize);
    }

    @PreAuthorize("@ss.hasPermi('exam:result:doMark')")
    @PostMapping(value = "/doMark")
    @ApiOperation(value = "打分")
    public Result doMark(@RequestBody MarkScoreModel markScoreModel) {
        return examResultService.doMark(markScoreModel);
    }

}
