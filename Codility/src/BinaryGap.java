public class BinaryGap {
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
}
