package com.ning.interfaces.controller;

import com.ning.application.assembler.PaperAssembler;
import com.ning.application.dto.PaperDTO;
import com.ning.application.dto.PaperTypeDTO;
import com.ning.application.service.PaperAppService;
import com.ning.common.model.ExamTestPaperModel;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.interfaces.request.AddPaperRequest;
import com.ning.service.ExamTestPaperService;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 试卷控制器
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/papers")
public class PaperController {

    private final PaperAppService paperAppService;
    private final PaperAssembler paperAssembler = PaperAssembler.INSTANCE;

    ExamTestPaperService examTestPaperService;

    @ApiOperation(value = "查询试卷类型")
    @GetMapping(value = "/type")
    public Result<List<PaperTypeDTO>> types() {
        return Result.ok(paperAppService.types());
    }

    @ApiOperation(value = "添加试卷")
    @PostMapping(value = "")
    public Result<PaperDTO> add(@RequestBody @Valid AddPaperRequest request) {
        PaperDTO paperDTO = paperAssembler.toDTO(request);
        return Result.ok(paperAppService.add(paperDTO));
    }

    //    @PreAuthorize("@ss.hasPermi('exam:testPaper:page')")
    @ApiOperation(value = "分页查询试卷列表")
    @GetMapping(value = "/page")
    public Result<PageWrapper<PaperDTO>> page(@RequestParam(value = "type", required = false) @ApiParam(name = "type", example = "") Integer type,
                                              @RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                              @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return Result.ok(paperAppService.page(type, keyword, pageNum, pageSize));
    }

    @PreAuthorize("@ss.hasPermi('exam:testPaper:preview')")
    @GetMapping(value = "/preview")
    @ApiOperation(value = "预览试卷")
    public Result<ExamTestPaperModel> preview(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return examTestPaperService.preview(id);
    }

    @PreAuthorize("@ss.hasPermi('exam:testPaper:listExam')")
    @GetMapping(value = "/listExam")
    @ApiOperation(value = "查询考试列表")
    public Result listExam(@RequestParam(value = "type", required = false) @ApiParam(name = "type", example = "") String type,
                           @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                           @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return examTestPaperService.listExam(type, pNum, pSize, SecurityUtils.getLoginUser().getUserId().intValue());
    }

    @PostMapping(value = "/submit")
    @ApiOperation(value = "交卷")
    public Result submit(@RequestBody @Valid ExamTestPaperModel examTestPaperModel) {
        return examTestPaperService.submit(examTestPaperModel);
    }

    @GetMapping(value = "/publish")
    @ApiOperation(value = "发布试卷")
    public Result publish(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return examTestPaperService.publish(id);
    }

    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除试卷")
    public Result delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return examTestPaperService.delete(id);
    }

}