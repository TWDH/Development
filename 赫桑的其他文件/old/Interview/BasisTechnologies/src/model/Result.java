package model;

import java.util.List;

public class Result {
    private List<Campaign> campaignList;
    private List<EvaluationResult> evaluationResultList;
    private Integer bidProcessed;
    private Long evaluationTime;

    public Result() {
    }

    public Result(List<Campaign> campaignList, List<EvaluationResult> evaluationResultList, Integer bidProcessed, Long evaluationTime) {
        this.campaignList = campaignList;
        this.evaluationResultList = evaluationResultList;
        this.bidProcessed = bidProcessed;
        this.evaluationTime = evaluationTime;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    public List<EvaluationResult> getEvaluationResultList() {
        return evaluationResultList;
    }

    public void setEvaluationResultList(List<EvaluationResult> evaluationResultList) {
        this.evaluationResultList = evaluationResultList;
    }

    public Integer getBidProcessed() {
        return bidProcessed;
    }

    public void setBidProcessed(Integer bidProcessed) {
        this.bidProcessed = bidProcessed;
    }

    public Long getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Long evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    @Override
    public String toString() {
        return "Result{" +
                "campaignList=" + campaignList +
                ", evaluationResultList=" + evaluationResultList +
                ", bidProcessed=" + bidProcessed +
                ", evaluationTime=" + evaluationTime +
                '}';
    }
}
