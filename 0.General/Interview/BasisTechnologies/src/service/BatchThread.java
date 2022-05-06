package service;

import model.Bid;
import model.Campaign;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public class BatchThread {
    public static void main(String[] args) {

        // init data
        List<Campaign> campaignList = new ArrayList<>();
        List<String> dimensionList = new ArrayList<String>() {{
            add("300x250");
            add("600x200");
        }};
        Campaign campaign1 = new Campaign(1, "CA", "apple.com", dimensionList);
        Campaign campaign2 = new Campaign(2, "CA", "youtube.com", dimensionList);
        Campaign campaign3 = new Campaign(2, "CN", "baidu.com", dimensionList);
        Campaign campaign4 = new Campaign(2, "CN", "tencent.com", dimensionList);
        Campaign campaign5 = new Campaign(2, "USA", "google.com", dimensionList);
        Campaign campaign6 = new Campaign(2, "USA", "meta.com", dimensionList);

        campaignList.add(campaign1);
        campaignList.add(campaign2);
        campaignList.add(campaign3);
        campaignList.add(campaign4);
        campaignList.add(campaign5);
        campaignList.add(campaign6);

        Bid bid = new Bid(1, "http://apple.com/ca/store?item=1290", "CA", "300x250");

        // multi-threading

        int threadNum = 2;
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
