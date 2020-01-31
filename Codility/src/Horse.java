public class Horse {
    public static void main(String[] args) {
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                int count = solution(x, y);
                System.out.println(count);
            }
            System.out.println("=============");
        }
        int x = 1;
        int y = 2;
        int count = solution(x, y);
        System.out.println(count);
    }

    public static int solution(int x, int y) {
        int size = 8;
        int[][] A = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int stepX = x + A[i][0];
            int stepY = y + A[i][1];

            if (stepX > 0 && stepY > 0
                    && stepX < size && stepY < size)
                count += 1;
        }

        return count;
    }
}
