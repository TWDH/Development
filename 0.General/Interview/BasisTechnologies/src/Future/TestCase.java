package Future;

import model.Bid;
import model.Campaign;
import model.EvaluationResult;

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
            // if (campaignList.contains(campaign)) {
            //     continue;
            // }
            campaignList.add(campaign);
        }

        // make sure we have a valid campaign
        campaignList.add(new Campaign(size + 1, "CA", "apple.com", dimensionList));

        return campaignList;
    }

    public static void main(String[] args) {
        // init data
        List<Campaign> campaignList = generateCampaign(100000);
        Bid bid = new Bid(1, "http://apple.com/ca/store?item=1290", "CA", "300x250");

        // setup thread count
        MultiThreadUtils multiThreadUtils = MultiThreadUtils.newInstance(200);
        List<EvaluationResult> executeResult = multiThreadUtils.execute(bid, campaignList);

        System.out.println("========= Evaluation Result ===========");
        for (EvaluationResult evaluationResult : executeResult) {
            System.out.println(evaluationResult);
        }

    }
}
