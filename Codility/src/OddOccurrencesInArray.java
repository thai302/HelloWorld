import java.util.*;

public class OddOccurrencesInArray {
    public int solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + "," + String.valueOf(i));
            } else
                map.put(A[i], String.valueOf(i));
        }

        int result = 0;
        for (String str : map.values()) {
            String[] split = str.split(",");
            if (split.length % 2 != 0) {
                result = A[Integer.valueOf(split[0])];
            }
        }

        return result;
    }
}
