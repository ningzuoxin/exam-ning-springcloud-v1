package com.ning.interfaces.controller;

import com.ning.application.assembler.PaperResultAssembler;
import com.ning.application.dto.PaperQuestionResultGradingDTO;
import com.ning.application.dto.PaperResultDTO;
import com.ning.application.dto.PaperResultDetailDTO;
import com.ning.application.service.PaperResultAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.interfaces.request.PaperResultGradingRequest;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试卷结果控制器
 *
 * @author zuoxin.ning
 * @since 2024-11-28 08:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/paper-results")
public class PaperResultController {

    private final PaperResultAppService paperResultAppService;
    private final PaperResultAssembler paperResultAssembler = PaperResultAssembler.INSTANCE;

    @ApiOperation(value = "查询用户的试卷结果列表")
    @GetMapping(value = "/by-paper")
    public Result<List<PaperResultDTO>> getUserPaperResult(@RequestParam(value = "id", defaultValue = "1") @ApiParam(name = "id", example = "1") Long id) {
        Long userUid = SecurityUtils.getLoginUser().getUserId();
        return Result.ok(paperResultAppService.getUserPaperResult(userUid, id));
    }

    //    @PreAuthorize("@ss.hasPermi('exam:result:detail')")
    @ApiOperation(value = "查询试卷结果详情")
    @GetMapping(value = "/{id}")
    public Result<PaperResultDetailDTO> detail(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(paperResultAppService.getResultDetail(id));
    }

    //    @PreAuthorize("@ss.hasPermi('exam:result:marks')")
    @ApiOperation(value = "查询待批阅试卷列表")
    @GetMapping(value = "/to-grading")
    public Result<PageWrapper<PaperResultDTO>> toGrading(@RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return Result.ok(paperResultAppService.toGrading(pageNum, pageSize));
    }

    //    @PreAuthorize("@ss.hasPermi('exam:result:doMark')")
    @ApiOperation(value = "评分")
    @PostMapping(value = "/grading")
    public Result<PaperResultDTO> grading(@RequestBody PaperResultGradingRequest request) {
        Long paperResultId = request.getPaperResultId();
        List<PaperQuestionResultGradingDTO> questionGradingList = paperResultAssembler.toGradingDTOList(request.getQuestionGradingList());
        return Result.ok(paperResultAppService.grading(paperResultId, questionGradingList));
    }

}
