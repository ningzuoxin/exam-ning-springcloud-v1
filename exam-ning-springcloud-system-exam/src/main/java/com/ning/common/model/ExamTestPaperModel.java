package com.ning.common.model;

import com.ning.entity.ExamQuestion;
import com.ning.entity.ExamTestPaper;
import com.ning.entity.ExamTestPaperItem;
import lombok.Data;

import java.util.List;

@Data
public class ExamTestPaperModel extends ExamTestPaper {

    private List<ExamTestPaperItem> examTestPaperItems;

    private List<ExamQuestion> examQuestions;

}
