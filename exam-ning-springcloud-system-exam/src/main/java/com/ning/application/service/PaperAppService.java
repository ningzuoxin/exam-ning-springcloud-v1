package com.ning.application.service;

import com.ning.application.assembler.PaperAssembler;
import com.ning.application.dto.PaperDTO;
import com.ning.application.dto.PaperTypeDTO;
import com.ning.domain.entity.Paper;
import com.ning.domain.enums.PaperTypeEnum;
import com.ning.domain.repository.PaperRepository;
import com.ning.domain.types.PaperId;
import com.ning.infrastructure.common.model.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
    private final PaperAssembler paperAssembler = PaperAssembler.INSTANCE;

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

}
