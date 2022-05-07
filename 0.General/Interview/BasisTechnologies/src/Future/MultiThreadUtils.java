package Future;

import model.Bid;
import model.Campaign;
import model.EvaluationResult;
import model.Result;
import service.SplitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public class MultiThreadUtils {

    // Thread Number (default = 5)
    private int threadCount = 5;

    // Pool Service
    private CompletionService<List<EvaluationResult>> pool = null;

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    // init thread pool and num of thread
    public static MultiThreadUtils newInstance(int threadCount) {
        MultiThreadUtils instance = new MultiThreadUtils();
        instance.setThreadCount(threadCount);

        threadCount = threadCount;

        return instance;
    }

    public List<EvaluationResult> execute(Bid biddingRequest, List<Campaign> campaignList) {

        // Create Thread Pool
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        // Create Executor completion pool
        pool = new ExecutorCompletionService<List<EvaluationResult>>(threadPool);

        // start time
        long startTime = System.currentTimeMillis();

        // data size
        int length = campaignList.size();

        // split
        List<List<Campaign>> pageList = SplitUtil.splitList(campaignList, threadCount);

        // multi-thread processing
        for (int i = 0; i < pageList.size(); i++) {
            // data for each thread
            List<Campaign> campaigns = pageList.get(i);

            // allocate to different threads
            EvaluateFutureThread execute = new EvaluateFutureThread<Campaign>(String.valueOf(i), biddingRequest, campaigns, null, null);

            // join the thread
            pool.submit(execute);
        }

        // return all the result
        List<EvaluationResult> evaluationResultFromAllThread = new ArrayList<>();
        for (int i = 0; i < pageList.size(); i++) {
            List<EvaluationResult> threadResult;

            try {
                // collect data from all thread
                threadResult = pool.take().get();
                evaluationResultFromAllThread.addAll(threadResult);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // close thread pool
        long endTime = System.currentTimeMillis();
        System.out.println("Total processing time is: " + (endTime - startTime));

        return evaluationResultFromAllThread;
    }

}
