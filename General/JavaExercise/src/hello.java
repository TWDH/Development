import java.util.*;

/**
 * @Author He Zhu
 * @Date 2022-01-20 11:05 a.m.
 * @Version 1.0
 */
public class hello {
    public static void main(String[] args) {
        List<List<String>> resList = new ArrayList<>();
        List<String> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        String.join()

        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> s3 = new ArrayList<>();
        List<String> s4 = new ArrayList<>();

        s1.add("cart");
        s1.add("maps");
        s1.add("home");

        s2.add("home");
        s2.add("cart");
        s2.add("maps");

        s3.add("home");
        s3.add("about");
        s3.add("career");

        s4.add("home");
        s4.add("maps");
        s4.add("maps");

        resList.add(s1);
        resList.add(s2);
        resList.add(s3);
        resList.add(s4);

        Collections.sort(resList, (o1, o2) -> {
            for(int i = 0; i < o1.size(); i++){
                if (!o1.get(i).equals(o2.get(i))) {
                    return o1.get(i).compareTo(o2.get(i));
                }
            }
            return 0;
        });

        Arrays.toString(resList.toArray());

        Set<Integer> set = new HashSet<>();

        Queue<Integer> pq = new PriorityQueue<>();

        int[] nums = new int[]{3354, 4316, 3259, 4904, 4598, 474, 3166, 6322, 8080, 9009};
        Arrays.sort(nums);
        System.out.println(nums);

    }
}
