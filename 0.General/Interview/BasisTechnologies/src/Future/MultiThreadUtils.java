package Future;

import model.Bid;
import model.Campaign;
import model.Result;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public class MultiThreadUtils {

    // Thread Number (default = 5)
    private int threadCount = 5;

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

    public Result execute(Bid biddingRequest, List<Campaign> campaignList) {

        // Create Thread Pool
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        //
    }

}
