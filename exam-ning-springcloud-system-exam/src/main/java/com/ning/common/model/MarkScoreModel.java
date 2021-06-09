package com.ning.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * 问答题打分对象
 */
public class MarkScoreModel implements Serializable {

    // 试卷结果id
    private Integer resultId;

    // 试卷打分项目
    private List<MarkScoreItemModel> markScoreItems;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public List<MarkScoreItemModel> getMarkScoreItems() {
        return markScoreItems;
    }

    public void setMarkScoreItems(List<MarkScoreItemModel> markScoreItems) {
        this.markScoreItems = markScoreItems;
    }

    public static class MarkScoreItemModel implements Serializable {

        // 试卷结果项目id
        private Integer id;

        // 分数
        private Float score;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Float getScore() {
            return score;
        }

        public void setScore(Float score) {
            this.score = score;
        }
    }

}
