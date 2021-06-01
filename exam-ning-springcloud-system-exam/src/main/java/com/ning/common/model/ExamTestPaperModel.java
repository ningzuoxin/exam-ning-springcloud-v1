package com.ning.common.model;

import com.ning.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class ExamTestPaperModel extends ExamTestPaper {

    private ExamTestPaperResult examTestPaperResult;

    private List<ExamTestPaperItem> examTestPaperItems;

    private List<ExamQuestion> examQuestions;

    private List<ExamTestPaperItemResult> examTestPaperItemResults;

}
