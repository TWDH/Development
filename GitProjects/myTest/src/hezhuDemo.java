import java.util.Arrays;

/**
 * @Author He Zhu
 * @Date 2021-09-13 10:57 a.m.
 * @Version 1.0
 */
public class hezhuDemo {

    public static void main(String[] args) {
        String str1 = "hezhukevin@gmail.com,hezhukevin2@gmail.com,hezhukevin3@gmail.com";
        String str2 = "hezhukevin@gmail.com, hezhukevin2@gmail.com, hezhukevin3@gmail.com";
        String str3 = " hezhukevin@gmail.com , hezhukevin2@gmail.com , hezhukevin3@gmail.com ";

        System.out.println("===== str1 =====");
        String[] split1 = str1.split(",");
        for (String s : split1) {
            System.out.println(s.trim());
        }
        System.out.println("===== str2 =====");
        String[] split2 = str2.split(",");
        for (String s : split2) {
            System.out.println(s.trim());
        }
        System.out.println("===== str3 =====");
        String[] split3 = str3.split(",");
        for (String s : split3) {
            String trim = s.trim();
        }
    }
}
