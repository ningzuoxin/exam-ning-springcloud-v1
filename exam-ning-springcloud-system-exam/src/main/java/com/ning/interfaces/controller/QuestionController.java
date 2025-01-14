package com.ning.interfaces.controller;

import com.ning.application.assembler.QuestionAssembler;
import com.ning.application.dto.QuestionDTO;
import com.ning.application.dto.QuestionTypeDTO;
import com.ning.application.service.QuestionAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddQuestionRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
public class QuestionController {

    private final QuestionAppService questionAppService;
    private final QuestionAssembler questionAssembler = QuestionAssembler.INSTANCE;

    @ApiOperation(value = "查询题型列表")
    @GetMapping(value = "/types")
    public List<QuestionTypeDTO> types() {
        return questionAppService.types();
    }

    //    @PreAuthorize("@ss.hasPermi('exam:question:page')")
    @ApiOperation(value = "分页查询试题列表")
    @GetMapping(value = "/page")
    public PageWrapper<QuestionDTO> page(@RequestParam(value = "type", required = false) @ApiParam(name = "type", example = "") String type,
                                         @RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                         @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return questionAppService.page(type, keyword, pageNum, pageSize);
    }

    //    @PreAuthorize("@ss.hasPermi('exam:question:add')")
    @ApiOperation(value = "添加试题")
    @PostMapping(value = "")
    public QuestionDTO add(@RequestBody @Valid AddQuestionRequest request) {
        QuestionDTO questionDTO = questionAssembler.toDTO(request);
        return questionAppService.add(questionDTO);
    }

    @ApiOperation(value = "删除试题")
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return questionAppService.delete(id);
    }

    @ApiOperation(value = "查询试题")
    @GetMapping(value = "/{id}")
    public QuestionDTO get(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return questionAppService.get(id);
    }

}
