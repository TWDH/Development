package service;

import model.Bid;
import model.Campaign;
import model.EvaluationResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public class EvaluateThread implements Runnable{

    private Bid biddingRequest;

    private List<Campaign> campaignList;

    public EvaluateThread(Bid biddingRequest, List<Campaign> campaignList) {
        this.biddingRequest = biddingRequest;
        this.campaignList = campaignList;
    }

    @Override
    public void run() {
        List<EvaluationResult> evaluationResultList = new ArrayList<>();
        synchronized (evaluationResultList) {
            for (Campaign campaign : campaignList) {
                Integer campaignId = campaign.getCampaignId();
                String targetCountry = campaign.getTargetCountry();
                String targetDomain = campaign.getTargetDomain();
                List<String> dimensions = campaign.getDimensions();

                // evaluate country
                if (!biddingRequest.getCountry().equals(targetCountry)) {
                    continue;
                }

                // evaluate URL
                try {
                    if (!new URL(biddingRequest.getPageURL()).getHost().equals(targetDomain)) {
                        continue;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                // evaluate dimensions
                if (!dimensions.contains(biddingRequest.getDimension())) {
                    continue;
                }

                // All passed
                EvaluationResult evaluationResult = new EvaluationResult(biddingRequest, campaignId);
                evaluationResultList.add(evaluationResult);
                System.out.println(evaluationResult);
            }
        }
    }
}
