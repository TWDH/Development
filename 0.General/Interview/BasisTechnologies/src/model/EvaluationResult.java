package model;

public class EvaluationResult {
    public Bid bid;
    Integer campaignId;

    public EvaluationResult() {
    }

    public EvaluationResult(Bid bid, Integer campaignId) {
        this.bid = bid;
        this.campaignId = campaignId;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public String toString() {
        return "EvaluationResult{" +
                "bid=" + bid +
                ", campaignId=" + campaignId +
                '}';
    }
}
