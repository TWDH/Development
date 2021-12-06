import java.lang.reflect.Array;
import java.util.Arrays;

public class sortExercise {
    public static void main(String[] args) {
        int[] A = new int[]{3, 2, 4, 1};
        Integer[] B = new Integer[]{1,2,3,4};
        Arrays.sort(B, (i, j) -> A[j - 1] - A[i - 1]);

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
    }
}
