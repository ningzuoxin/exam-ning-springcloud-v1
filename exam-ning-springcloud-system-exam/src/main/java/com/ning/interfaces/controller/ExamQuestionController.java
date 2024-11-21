package com.ning.interfaces.controller;

import cn.hutool.core.date.DateUtil;
import com.ning.application.dto.QuestionTypeDTO;
import com.ning.application.service.QuestionAppService;
import com.ning.entity.ExamQuestion;
import com.ning.infrastructure.common.model.Result;
import com.ning.service.ExamQuestionService;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 试题控制器
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/questions")
public class ExamQuestionController {

    private final QuestionAppService questionAppService;

    ExamQuestionService examQuestionService;

    @ApiOperation(value = "查询题型列表")
    @GetMapping(value = "/types")
    public Result<List<QuestionTypeDTO>> typeList() {
        return Result.ok(questionAppService.findTypeList());
    }

    @PreAuthorize("@ss.hasPermi('exam:question:page')")
    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询考题列表")
    public Result selectPage(@RequestParam(value = "type", required = false) @ApiParam(name = "type", example = "") String type,
                             @RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                             @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                             @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return examQuestionService.selectExamQuestionPage(type, keyword, pNum, pSize);
    }

    @PreAuthorize("@ss.hasPermi('exam:question:add')")
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加考题")
    public Result add(@RequestBody @Valid ExamQuestion examQuestion) {
        int now = (int) DateUtil.currentSeconds();
        examQuestion.setIsDelete(0);
        examQuestion.setCreateTime(now);
        examQuestion.setUpdateTime(now);
        examQuestion.setCreateUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        examQuestion.setUpdateUserId(SecurityUtils.getLoginUser().getUserId().intValue());
        return examQuestionService.add(examQuestion);
    }

    @PostMapping(value = "/updateIsShow")
    @ApiOperation(value = "修改考题显示状态")
    public Result updateIsShow(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id,
                               @RequestParam(value = "isShow") @ApiParam(name = "isShow", example = "1") Integer isShow) {
        return examQuestionService.updateIsShow(id, isShow);
    }

    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除考题")
    public Result delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return examQuestionService.delete(id);
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "考题详情")
    public Result detail(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return examQuestionService.detail(id);
    }

}
