package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Bid;
import model.Campaign;
import model.EvaluationResult;
import model.Result;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class evaluate {
    public static Result evaluateBidding(List<Bid> bidList, List<Campaign> campaignList) throws MalformedURLException {

        // String pattern = "[^(?:http:\\/\\/|www\\.|https:\\/\\/)]([^\\/]+)";

        List<Campaign> campaignResult = new ArrayList<>();
        Result result = new Result();
        List<EvaluationResult> evaluationResultList = new ArrayList<>();

        boolean[] visited = new boolean[bidList.size()];

        int bidSize = bidList.size();

        long start = System.currentTimeMillis();

        for (Campaign campaign : campaignList) {
            Integer campaignId = campaign.getCampaignId();
            String targetCountry = campaign.getTargetCountry();
            String targetDomain = campaign.getTargetDomain();
            List<String> dimensions = campaign.getDimensions();

            for (int i = 0; i < bidSize;i ++) {
                Bid bid = bidList.get(i);
                // skip
                if(visited[i]){
                    continue;
                }
                if (!bid.getCountry().equals(targetCountry)) {
                    continue;
                }
                if (!new URL(bid.getPageURL()).getHost().equals(targetDomain)) {
                    continue;
                }
                if (!dimensions.contains(bid.getDimension())) {
                    continue;
                }

                EvaluationResult evaluationResult = new EvaluationResult(bid, campaignId);
                evaluationResultList.add(evaluationResult);
                // remove the bid that has been processed
                visited[i] = true;
            }

            campaignResult.add(campaign);
        }

        long end = System.currentTimeMillis();

        result.setCampaignList(campaignResult);
        result.setEvaluationResultList(evaluationResultList);
        result.setBidProcessed(bidSize);
        result.setEvaluationTime(end - start);

        return result;
    }

    public static void main(String[] args) throws JsonProcessingException, MalformedURLException {
        List<Bid> bidList = new ArrayList<>();
        Bid bid1 = new Bid(1, "http://apple.com/ca/store?item=1290", "CA", "300x250");
        Bid bid2 = new Bid(2, "https://youtube.com/", "CA", "600x200");
        bidList.add(bid1);
        bidList.add(bid2);

        List<Campaign> campaignList = new ArrayList<>();
        List<String> dimensionList = new ArrayList<>() {{
            add("300x250");
            add("600x200");
        }};
        Campaign campaign1 = new Campaign(1, "CA", "apple.com", dimensionList);
        Campaign campaign2 = new Campaign(2, "CA", "youtube.com", dimensionList);

        campaignList.add(campaign1);
        campaignList.add(campaign2);

        Result result = evaluateBidding(bidList, campaignList);

        ObjectMapper objectMapper = new ObjectMapper();

        String s = objectMapper.writeValueAsString(result);
        System.out.println(s);
    }
}
