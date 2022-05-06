package model;

import java.util.List;
import java.util.Objects;

public class Campaign {
    private Integer campaignId;

    private String targetCountry;

    private String targetDomain;

    private List<String> dimensions;

    public Campaign() {
    }

    public Campaign(Integer campaignId, String targetCountry, String targetDomain, List<String> dimensions) {
        this.campaignId = campaignId;
        this.targetCountry = targetCountry;
        this.targetDomain = targetDomain;
        this.dimensions = dimensions;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }


    public String getTargetCountry() {
        return targetCountry;
    }

    public void setTargetCountry(String targetCountry) {
        this.targetCountry = targetCountry;
    }

    public String getTargetDomain() {
        return targetDomain;
    }

    public void setTargetDomain(String targetDomain) {
        this.targetDomain = targetDomain;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return  Objects.equals(targetCountry, campaign.targetCountry) && Objects.equals(targetDomain, campaign.targetDomain) && Objects.equals(dimensions, campaign.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, targetCountry, targetDomain, dimensions);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId=" + campaignId +
                ", targetCountry='" + targetCountry + '\'' +
                ", targetDomain='" + targetDomain + '\'' +
                ", dimensions=" + dimensions +
                '}';
    }
}
