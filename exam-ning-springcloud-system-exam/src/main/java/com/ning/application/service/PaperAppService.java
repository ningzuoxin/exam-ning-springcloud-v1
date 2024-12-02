package com.ning.application.service;

import com.ning.application.assembler.PaperAssembler;
import com.ning.application.assembler.PaperQuestionAssembler;
import com.ning.application.assembler.QuestionAssembler;
import com.ning.application.dto.PaperDTO;
import com.ning.application.dto.PaperPreviewDTO;
import com.ning.application.dto.PaperQuestionSubmitDTO;
import com.ning.application.dto.PaperTypeDTO;
import com.ning.constant.BusinessCodeEnum;
import com.ning.domain.entity.*;
import com.ning.domain.enums.PaperTypeEnum;
import com.ning.domain.repository.*;
import com.ning.domain.types.*;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 试卷应用服务
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:00
 */
@Service
@RequiredArgsConstructor
public class PaperAppService {

    private final PaperRepository paperRepository;
    private final QuestionRepository questionRepository;
    private final PaperResultRepository paperResultRepository;
    private final PaperQuestionRepository paperQuestionRepository;
    private final PaperQuestionResultRepository paperQuestionResultRepository;
    private final PaperAssembler paperAssembler = PaperAssembler.INSTANCE;
    private final QuestionAssembler questionAssembler = QuestionAssembler.INSTANCE;
    private final PaperQuestionAssembler paperQuestionAssembler = PaperQuestionAssembler.INSTANCE;

    /**
     * 查询试卷类型列表
     *
     * @return 试卷类型列表
     */
    public List<PaperTypeDTO> types() {
        return Arrays.stream(PaperTypeEnum.values())
                .map(t -> PaperTypeDTO.builder().type(t.getType()).title(t.getTitle()).build())
                .collect(Collectors.toList());
    }

    /**
     * 添加试卷
     *
     * @param paperDTO 试卷
     * @return 试卷
     */
    public PaperDTO add(PaperDTO paperDTO) {
        Paper paper = new Paper(
                new PaperId(null),
                paperDTO.getType(),
                paperDTO.getTitle(),
                paperDTO.getTimeLimit(),
                paperDTO.getFrequencyLimit(),
                paperDTO.getTotalScore(),
                paperDTO.getPassedScore(),
                paperDTO.getQuestionCount(),
                new PaperId(paperDTO.getCopyPaperId()));
        paper = paperRepository.save(paper);
        return paperAssembler.toDTO(paper);
    }

    /**
     * 分页查询试卷列表
     *
     * @param type     试卷类型
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试卷列表
     */
    public PageWrapper<PaperDTO> page(Integer type, String keyword, Integer pageNum, Integer pageSize) {
        PageWrapper<Paper> pagePaperList = paperRepository.findByPage(type, keyword, pageNum, pageSize);
        return paperAssembler.toDTOPageList(pagePaperList);
    }

    /**
     * 发布试卷
     *
     * @param paperUid 试卷 ID
     * @return 试卷
     */
    public PaperDTO publish(Long paperUid) {
        Optional<Paper> paperOpt = paperRepository.find(new PaperId(paperUid));
        if (!paperOpt.isPresent()) {
            throw new BusinessException(BusinessCodeEnum.PAPER_NOT_EXISTS);
        }

        Paper paper = paperOpt.get();
        paper.publish();
        paper = paperRepository.save(paper);
        return paperAssembler.toDTO(paper);
    }

    /**
     * 删除试卷
     *
     * @param paperUid 试卷 ID
     * @return 是否操作成功
     */
    public boolean delete(Long paperUid) {
        return paperRepository.remove(new PaperId(paperUid));
    }

    /**
     * 试卷预览
     *
     * @param paperUid 试卷 ID
     * @return 试卷预览
     */
    public PaperPreviewDTO preview(Long paperUid) {
        Optional<Paper> paperOpt = paperRepository.find(new PaperId(paperUid));
        if (!paperOpt.isPresent()) {
            throw new BusinessException(BusinessCodeEnum.PAPER_NOT_EXISTS);
        }

        Paper paper = paperOpt.get();

        // 查询试卷试题列表
        List<PaperQuestion> paperQuestionList = paperQuestionRepository.find(paper.getId());

        // 查询试题列表
        List<QuestionId> questionIdList = paperQuestionList.stream().map(PaperQuestion::getQuestionId).collect(Collectors.toList());
        List<Question> questionList = questionRepository.find(questionIdList);

        return PaperPreviewDTO.builder()
                .paper(paperAssembler.toDTO(paper))
                .paperQuestionList(paperQuestionAssembler.toDTOList(paperQuestionList))
                .questionList(questionAssembler.toDTOList(questionList))
                .build();
    }

    /**
     * 分页查询用户的试卷列表
     *
     * @param type     试卷类型
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @param userUid  用户 ID
     * @return 试卷列表
     */
    public PageWrapper<PaperDTO> findUserPaperByPage(Integer type, Integer pageNum, Integer pageSize, Long userUid) {
        // todo: 根据 userUid 查询出用户每份试卷做了几次
        PageWrapper<Paper> pagePaperList = paperRepository.findByPage(type, null, pageNum, pageSize);
        return paperAssembler.toDTOPageList(pagePaperList);
    }

    /**
     * 提交试卷
     *
     * @param paperUid           试卷 ID
     * @param timeUsed           答题耗时
     * @param questionSubmitList 试卷试题提交请求列表
     * @return 是否操作成功
     */
    public boolean submit(Long paperUid, Integer timeUsed, List<PaperQuestionSubmitDTO> questionSubmitList) {
        Optional<Paper> paperOpt = paperRepository.find(new PaperId(paperUid));
        if (!paperOpt.isPresent()) {
            throw new BusinessException(BusinessCodeEnum.PAPER_NOT_EXISTS);
        }

        Paper paper = paperOpt.get();

        // 保存试卷结果
        PaperResultId paperResultId = new PaperResultId(null);
        PaperId paperId = new PaperId(paperUid);
        Long userUid = SecurityUtils.getLoginUser().getUserId();

        PaperResult paperResult = new PaperResult(paperResultId, paperId, paper.getTitle(), userUid, timeUsed);
        paperResult = paperResultRepository.save(paperResult);

        List<PaperQuestionId> paperQuestionIdList = questionSubmitList.stream().map(s -> new PaperQuestionId(s.getPaperQuestionId())).collect(Collectors.toList());
        List<PaperQuestion> paperQuestionList = paperQuestionRepository.find(paperQuestionIdList);
        Map<PaperQuestionId, PaperQuestion> paperQuestionMap = paperQuestionList.stream().collect(Collectors.toMap(PaperQuestion::getId, Function.identity()));

        // 保存试卷试题结果
        for (PaperQuestionSubmitDTO questionSubmit : questionSubmitList) {
            PaperQuestionResultId paperQuestionResultId = new PaperQuestionResultId(null);
            paperResultId = paperResult.getId();
            PaperQuestionId paperQuestionId = new PaperQuestionId(questionSubmit.getPaperQuestionId());
            PaperQuestion paperQuestion = paperQuestionMap.get(paperQuestionId);

            PaperQuestionResult paperQuestionResult = new PaperQuestionResult(paperQuestionResultId, paperResultId, paperId, paperQuestionId, paperQuestion.getQuestionId(), userUid, questionSubmit.getAnswer());
            paperQuestionResultRepository.save(paperQuestionResult);
        }
        return true;
    }

}
