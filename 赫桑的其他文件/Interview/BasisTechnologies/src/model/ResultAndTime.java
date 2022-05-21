package model;

import java.util.List;

public class ResultAndTime {
    List<EvaluationResult> evaluationResultList;
    Long processingTime;

    public ResultAndTime(List<EvaluationResult> evaluationResultList, Long processingTime){
        this.evaluationResultList = evaluationResultList;
        this.processingTime = processingTime;
    }

    public List<EvaluationResult> getEvaluationResultList() {
        return evaluationResultList;
    }

    public void setEvaluationResultList(List<EvaluationResult> evaluationResultList) {
        this.evaluationResultList = evaluationResultList;
    }

    public Long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }
}
