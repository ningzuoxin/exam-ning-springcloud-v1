package com.ning.application.service;

import com.ning.application.assembler.QuestionAssembler;
import com.ning.application.dto.QuestionDTO;
import com.ning.application.dto.QuestionTypeDTO;
import com.ning.constant.BusinessCodeEnum;
import com.ning.domain.entity.Question;
import com.ning.domain.enums.QuestionTypeEnum;
import com.ning.domain.repository.QuestionRepository;
import com.ning.domain.types.QuestionId;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 试题应用服务
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Service
@RequiredArgsConstructor
public class QuestionAppService {

    private final QuestionRepository questionRepository;
    private final QuestionAssembler questionAssembler = QuestionAssembler.INSTANCE;

    /**
     * 查询试题类型列表
     *
     * @return 试题类型列表
     */
    public List<QuestionTypeDTO> types() {
        return Arrays.stream(QuestionTypeEnum.values())
                .map(t -> QuestionTypeDTO.builder().type(t.getType()).title(t.getTitle()).build())
                .collect(Collectors.toList());
    }

    /**
     * 分页查询试题列表
     *
     * @param type     类型
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 试题列表
     */
    public PageWrapper<QuestionDTO> page(String type, String keyword, Integer pageNum, Integer pageSize) {
        PageWrapper<Question> pageQuestionList = questionRepository.findByPage(type, keyword, pageNum, pageSize);
        return questionAssembler.toDTOPageList(pageQuestionList);
    }

    /**
     * 添加试题
     *
     * @param questionDTO 试题
     * @return 试题
     */
    public QuestionDTO add(QuestionDTO questionDTO) {
        Question question = new Question(
                new QuestionId(null),
                questionDTO.getType(),
                questionDTO.getContent(),
                questionDTO.getCorrectAnswer(),
                questionDTO.getExplanation(),
                questionAssembler.toOptionEntityList(questionDTO.getOptions()));
        question = questionRepository.save(question);
        return questionAssembler.toDTO(question);
    }

    /**
     * 删除试题
     *
     * @param id 试题 ID
     * @return 是否操作成功
     */
    public boolean delete(Long id) {
        QuestionId questionId = new QuestionId(id);
        return questionRepository.remove(questionId);
    }

    /**
     * 查询试题
     *
     * @param id 试题 ID
     * @return 试题
     */
    public QuestionDTO get(Long id) {
        Optional<Question> questionOpt = questionRepository.find(new QuestionId(id));
        return questionOpt.map(questionAssembler::toDTO).orElseThrow(() -> new BusinessException(BusinessCodeEnum.QUESTION_NOT_EXISTS));
    }

}
