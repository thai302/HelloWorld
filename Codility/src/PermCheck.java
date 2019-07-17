import java.util.HashSet;
import java.util.Set;

public class PermCheck {
    public static int solution(int[] A) {
        // write your code in Java SE 8
//        int sum = 0;
        int min = 1;
        int max = A.length;
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (!hs.contains(A[i]))
                hs.add(A[i]);
//            sum += A[i];
            if (min > A[i])
                min = A[i];
            if (max < A[i])
                max = A[i];
        }

        if (hs.size() != A.length)
            return 0;
        else {
            if (max == A.length && min == 1)
                return 1;
            else
                return 0;
//            if (sum == (A.length * (A.length + 1) / 2))
//                return 1;
//            else
//                return 0;
        }
    }
}
