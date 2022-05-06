package service;

import model.Bid;
import model.Campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public class BatchThread {

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
        // for (Campaign campaign : campaignList) {
        //     System.out.println(campaign.toString());
        // }

        Bid bid = new Bid(1, "http://apple.com/ca/store?item=1290", "CA", "300x250");

        // multi-threading
        System.out.println(" ===== Evaluating =====");
        int threadNum = 200;
        List<List<Campaign>> pageList = SplitUtil.splitList(campaignList, threadNum);

        // open multi-thread
        for (int i = 0; i < pageList.size(); i++) {
            List<Campaign> campaigns = pageList.get(i);

            EvaluateThread evaluateThread = new EvaluateThread(bid, campaigns);
            Thread thread = new Thread(evaluateThread);
            thread.start();
        }
    }
}
