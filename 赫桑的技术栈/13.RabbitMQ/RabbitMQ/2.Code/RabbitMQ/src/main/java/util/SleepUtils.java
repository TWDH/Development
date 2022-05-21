package util;

/**
 * @Author He Zhu
 * @Date 2022-05-02
 * @Version 0.1
 */
public class SleepUtils {
    public static void sleep(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }
}
