public class CodilityMain {
    public static void main(String[] args) {
        int[] input = {5, 2, 4, 6, 3, 7};
        int result = solution1(input);
        System.out.println(result);
    }

    public static int solution1(int[] A) {
        // write your code in Java SE 8
        int min = A[1];
        int minPos = 1;
        for (int i = 2; i < A.length - 1; i++) {
            if (min > A[i]) {
                min = A[i];
                minPos = i;
            }
        }

        int secondMin;
        if (minPos == 1)
            secondMin = A[2];
        else
            secondMin = A[1];

        for (int i = 1; i < A.length - 1; i++) {
            if (Math.abs(minPos - i) > 1) {
                if (secondMin > A[i])
                    secondMin = A[i];
            }
        }

        return min + secondMin;
    }

    public int solution(int N) {
        // write your code in Java SE 8
        boolean isPos;
        if (N >= 0)
            isPos = true;
        else {
            isPos = false;
            N = (N * (-1));
        }

        char[] str = String.valueOf(N).toCharArray();
        char[] resultStr = new char[str.length + 1];

        int i = 0;
        for (i = 0; i < str.length; i++) {
            if ((isPos && str[i] <= '5') || (!isPos && str[i] >= '5')) {
                resultStr[i] = '5';
                break;
            } else
                resultStr[i] = str[i];
        }
        for (int k = i + 1; k < resultStr.length; k++) {
            resultStr[k] = str[k - 1];
        }

        int result = Integer.valueOf(String.valueOf(resultStr));
        return isPos ? result : (result * (-1));
    }
}
