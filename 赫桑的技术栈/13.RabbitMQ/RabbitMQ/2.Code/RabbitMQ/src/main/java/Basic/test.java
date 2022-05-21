package Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author He Zhu
 * @Date 2021-12-27 10:55 a.m.
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        PriorityQueue<Integer> queMin = new PriorityQueue<Integer>(2, (a, b) -> (b - a));
        queMin.offer(1);
        queMin.offer(2);
        queMin.offer(3);

        System.out.println(queMin.toString());

        int[] nums = new int[]{1, 2, 3};
        List<Integer> list = new ArrayList<Integer>();

    }


}
