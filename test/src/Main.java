import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int input = 1041;
//        int output = solution(input);
//        System.out.println(output);
        System.out.println("haha");
    }

    public static int solution(int N) {
        // write your code in Java SE 8
        int max = 0;
        String binary = Integer.toBinaryString(N);
        char[] chars = binary.toCharArray();
        for (int i = 0; i < chars.length - 2; i++) {
            if (chars[i] == '1' && chars[i + 1] == '0') {
                int count = 0;
                boolean flag = false;
                for (int k = i + 1; k < chars.length; k++) {
                    if (chars[k] == '0') {
                        count++;
                    } else {
                        i = k - 1;
                        flag = true;
                        break;
                    }
                }
                if (flag && (count > max)) max = count;
            }
        }

        return max;
    }

    public static int solutionFlippingMatrix(int[][] A) {
        int max = 1;
        Map<String, Integer> res = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            List<String> array1 = new ArrayList<>();
            List<String> array0 = new ArrayList<>();
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb0 = new StringBuilder();
            for (int k = 0; k < A[0].length; k++) {
                if (A[i][k] == 1)
                    sb1.append(String.valueOf(k)).append(",");
                else
                    sb0.append(String.valueOf(k)).append(",");
            }

            String str1 = sb1.toString();
            String str0 = sb0.toString();
            if (res.containsKey(str1)) {
                int temp = res.get(str1);
                res.put(str1, temp + 1);
                if (max < (temp + 1))
                    max = temp + 1;
            } else {
                res.put(str1, 1);
            }

            if (res.containsKey(str0)) {
                int temp = res.get(str0);
                res.put(str0, temp + 1);
                if (max < (temp + 1))
                    max = temp + 1;
            } else {
                res.put(str0, 1);
            }
        }
//        max = maxUsingCollectionsMaxAndLambda(res);
//        Map.Entry<String, Integer> maxEntry = Collections.max(res.entrySet(),
//                (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e1.getValue()
//                .compareTo(e2.getValue()));
//        return maxEntry.getValue();
//        for (Integer value : res.values()) {
//            if (max < value)
//                max = value;
//        }

        return max;
    }

    public static <K, V extends Comparable<V>> V maxUsingCollectionsMaxAndLambda(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = Collections.max(map.entrySet(), (Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> e1.getValue()
                .compareTo(e2.getValue()));
        return maxEntry.getValue();
    }
}
