package com.ning.interfaces.controller;

import com.ning.application.assembler.PaperAssembler;
import com.ning.application.dto.PaperDTO;
import com.ning.application.dto.PaperPreviewDTO;
import com.ning.application.dto.PaperQuestionSubmitDTO;
import com.ning.application.dto.PaperTypeDTO;
import com.ning.application.service.PaperAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.infrastructure.utils.SecurityUtils;
import com.ning.interfaces.request.AddPaperRequest;
import com.ning.interfaces.request.PaperSubmitRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
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

    //    @PreAuthorize("@ss.hasPermi('exam:testPaper:preview')")
    @ApiOperation(value = "试卷预览")
    @GetMapping(value = "/{id}/preview")
    public Result<PaperPreviewDTO> preview(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(paperAppService.preview(id));
    }

    //    @PreAuthorize("@ss.hasPermi('exam:testPaper:listExam')")
    @ApiOperation(value = "查询用户的试卷列表")
    @GetMapping(value = "/user-page")
    public Result<PageWrapper<PaperDTO>> userPaperPage(@RequestParam(value = "type", required = false) @ApiParam(name = "type", example = "") Integer type,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        Long userUid = SecurityUtils.getLoginUser().getUserId();
        return Result.ok(paperAppService.findUserPaperByPage(type, pageNum, pageSize, userUid));
    }

    @ApiOperation(value = "提交试卷")
    @PostMapping(value = "/{id}/submit")
    public Result<Boolean> submit(@PathVariable(value = "id") Long id, @RequestBody @Valid PaperSubmitRequest request) {
        List<PaperQuestionSubmitDTO> paperQuestionSubmitDTOList = paperAssembler.toQuestionSubmitDTOList(request.getQuestionSubmitList());
        return Result.ok(paperAppService.submit(id, request.getTimeUsed(), paperQuestionSubmitDTOList));
    }

    @ApiOperation(value = "发布试卷")
    @PostMapping(value = "/{id}/publish")
    public Result<PaperDTO> publish(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(paperAppService.publish(id));
    }

    @ApiOperation(value = "删除试卷")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(paperAppService.delete(id));
    }

}
