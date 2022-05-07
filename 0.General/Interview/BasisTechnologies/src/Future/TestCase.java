package Future;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class TestCase {

    public static List<Campaign> generateCampaign(int size) {
        List<Campaign> campaignList = new ArrayList<>();

        String[] countryArr = new String[]{"CA", "CN", "USA", "IT", "UK","AD","AE", "AG","AI", "AL", "AM", "AO", "AR", "AT", "AU"};
        String[] urlArr = new String[]{"apple.com", "youtube.com", "youtube.com", "tencent.com", "google.com", "meta.com"};
        List<String> dimensionList = new ArrayList<String>() {{
            add("300x250");
            add("600x200");
        }};

        for (int i = 1; i < size + 1; i++) {
            int randCountry = (int) (Math.random() * countryArr.length);
            String country = countryArr[randCountry];

            int randUrl = (int) (Math.random() * urlArr.length);
            String url = urlArr[randUrl];

            Campaign campaign = new Campaign(i, country, url, dimensionList);

            // remove duplicated Campaign
            if (campaignList.contains(campaign)) {
                continue;
            }
            campaignList.add(campaign);
        }

        // make sure we have a valid campaign
        campaignList.add(new Campaign(size + 1, "CA", "apple.com", dimensionList));

        return campaignList;
    }

    public static void main(String[] args) throws JsonProcessingException {
        // init data
        // create campaigns
        List<Campaign> campaignList = generateCampaign(100000);

        // create bidding request
        Bid bid = new Bid(1, "http://apple.com/ca/store?item=1290", "CA", "300x250");
        List<Bid> biddingRequestList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            biddingRequestList.add(bid);
        }

        Long totalProcessingTime = 0L;
        List<EvaluationResult> finalEvaluationResult = new ArrayList<>();

        for (int i = 0; i < biddingRequestList.size(); i++) {
            // get current bidding request
            Bid curBiddingRequest = biddingRequestList.get(i);

            // setup thread count
            MultiThreadUtils multiThreadUtils = MultiThreadUtils.newInstance(200);
            ResultAndTime resultAndTime = multiThreadUtils.execute(curBiddingRequest, campaignList);

            List<EvaluationResult> evaluationResultList = resultAndTime.getEvaluationResultList();
            finalEvaluationResult.addAll(evaluationResultList);

            System.out.println("========= Evaluation Result ===========");
            for (EvaluationResult evaluationResult : evaluationResultList) {
                System.out.println(evaluationResult);
            }

            Long processingTime = resultAndTime.getProcessingTime();
            System.out.println("========= Processing Time ===========");
            System.out.println(processingTime + "ms for each bidding request");

            totalProcessingTime += processingTime;
        }

        // Result Set
        Result result = new Result();
        result.setCampaignList(campaignList);
        result.setEvaluationResultList(finalEvaluationResult);
        result.setBidProcessed(biddingRequestList.size());
        result.setEvaluationTime(totalProcessingTime);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(result);
        System.out.println(s);

    }
}
