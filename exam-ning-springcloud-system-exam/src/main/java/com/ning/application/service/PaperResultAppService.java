package com.ning.application.service;

import com.ning.application.assembler.PaperResultAssembler;
import com.ning.application.dto.PaperQuestionResultGradingDTO;
import com.ning.application.dto.PaperResultDTO;
import com.ning.application.dto.PaperResultDetailDTO;
import com.ning.constant.BusinessCodeEnum;
import com.ning.domain.entity.PaperQuestion;
import com.ning.domain.entity.PaperQuestionResult;
import com.ning.domain.entity.PaperResult;
import com.ning.domain.enums.PaperResultStatusEnum;
import com.ning.domain.repository.PaperQuestionRepository;
import com.ning.domain.repository.PaperQuestionResultRepository;
import com.ning.domain.repository.PaperResultRepository;
import com.ning.domain.service.PaperResultGradingService;
import com.ning.domain.types.PaperId;
import com.ning.domain.types.PaperQuestionId;
import com.ning.domain.types.PaperQuestionResultId;
import com.ning.domain.types.PaperResultId;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 试卷结果应用服务
 *
 * @author zuoxin.ning
 * @since 2024-11-28 08:00
 */
@Service
@RequiredArgsConstructor
public class PaperResultAppService {

    private final PaperResultRepository paperResultRepository;
    private final PaperQuestionRepository paperQuestionRepository;
    private final PaperResultGradingService paperResultGradingService;
    private final PaperQuestionResultRepository paperQuestionResultRepository;
    private final PaperResultAssembler paperResultAssembler = PaperResultAssembler.INSTANCE;

    /**
     * 根据用户 ID 查询试卷结果列表
     *
     * @param userUid  用户 ID
     * @param paperUid 试卷 ID
     * @return 试卷结果列表
     */
    public List<PaperResultDTO> getUserPaperResult(Long userUid, Long paperUid) {
        List<PaperResult> paperResultList = paperResultRepository.findByUserId(userUid, new PaperId(paperUid));
        return paperResultAssembler.toDTOList(paperResultList);
    }

    /**
     * 试卷结果明细
     *
     * @param paperResultUid 试卷结果 ID
     * @return 试卷结果明细
     */
    public PaperResultDetailDTO getResultDetail(Long paperResultUid) {
        return null;
    }

    /**
     * 分页查询待阅卷试卷结果列表
     *
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试卷结果列表
     */
    public PageWrapper<PaperResultDTO> toGrading(Integer pageNum, Integer pageSize) {
        PageWrapper<PaperResult> pagePaperResult = paperResultRepository.findByPage(PaperResultStatusEnum.PENDING.getValue(), pageNum, pageSize);
        return paperResultAssembler.toDTOPageList(pagePaperResult);
    }

    /**
     * 评分
     *
     * @param paperResultUid      试卷结果 ID
     * @param questionGradingList 试题评分列表
     * @return 试卷结果
     */
    public PaperResultDTO grading(Long paperResultUid, List<PaperQuestionResultGradingDTO> questionGradingList) {
        Optional<PaperResult> paperResultOpt = paperResultRepository.find(new PaperResultId(paperResultUid));
        if (!paperResultOpt.isPresent()) {
            throw new BusinessException(BusinessCodeEnum.PAPER_RESULT_NOT_EXISTS);
        }

        PaperResult paperResult = paperResultOpt.get();

        // 被评分列表转 Map，Key 是试卷试题结果 ID，Value 是分数
        Map<Long, Float> questionGradingMap = questionGradingList.stream().collect(Collectors.toMap(PaperQuestionResultGradingDTO::getPaperQuestionResultId, PaperQuestionResultGradingDTO::getScore));

        // 查询被评分试卷试题结果列表
        List<PaperQuestionResultId> paperQuestionResultIdList = questionGradingList.stream()
                .map(t -> new PaperQuestionResultId(t.getPaperQuestionResultId()))
                .collect(Collectors.toList());
        List<PaperQuestionResult> paperQuestionResultList = paperQuestionResultRepository.find(paperQuestionResultIdList);
        paperQuestionResultList.forEach(r -> r.setScore(questionGradingMap.get(r.getId().getValue())));

        // 查询被评分试卷试题列表
        List<PaperQuestionId> paperQuestionIdList = paperQuestionResultList.stream().map(PaperQuestionResult::getPaperQuestionId).collect(Collectors.toList());
        List<PaperQuestion> paperQuestionList = paperQuestionRepository.find(paperQuestionIdList);

        // 评分
        paperResult = paperResultGradingService.doGrading(paperResult, paperQuestionList, paperQuestionResultList);
        return paperResultAssembler.toDTO(paperResult);
    }

}
