public class CyclicRotation {
    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        if (K == A.length)
            return A;
        else if (K > A.length)
            K = K % A.length;

        int[] result = new int[A.length];
        for (int i = 0; i < K; i++) {
            result[i] = A[A.length - (K - i)];
        }

        for (int i = K; i < result.length; i++) {
            result[i] = A[i - K];
        }


        return result;
    }
}
