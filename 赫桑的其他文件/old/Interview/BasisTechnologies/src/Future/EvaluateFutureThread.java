package Future;

import Future.ITask;
import model.Bid;
import model.Campaign;
import model.EvaluationResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public class EvaluateFutureThread<E> implements Callable<List<EvaluationResult>> {

    // Thread Name
    private String threadName = "";

    private Bid biddingRequest;

    // Data to be processed
    private List<Campaign> campaignList;

    // Auxiliary param
    private Map<String, Object> params;

    // Task to be executed
    private ITask<EvaluationResult, Campaign> task;

    // Constructor
    public EvaluateFutureThread(String threadName, Bid biddingRequest, List<Campaign> campaignList, Map<String, Object> params, ITask<EvaluationResult, Campaign> task) {
        this.threadName = threadName;
        this.biddingRequest = biddingRequest;
        this.campaignList = campaignList;
        this.params = params;
        this.task = task;
    }

    @Override
    public List<EvaluationResult> call() throws Exception {

        // Result Collection
        List<EvaluationResult> evaluationResultList = new ArrayList<>();

        System.out.println("Thread Name: [" + this.threadName + "] BEGIN processing number of [" + campaignList.size() + "] campaigns");

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
                // System.out.println(evaluationResult);
            }
        }

        System.out.println("Thread Name: [" + this.threadName + "] FINISHED processing [" + campaignList.size() + "] campaigns");

        return evaluationResultList;
    }
}
