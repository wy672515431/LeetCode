package LeetCode;

public class 翻转矩阵后的得分{

    public int matrixScore(int[][] grid) {
        int ans = 0;
        // 第一列按行翻转
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] ^= 1;
                }
            }
        }
        ans = Math.max(ans, getSum(grid));
        // 其余列按列翻转
        for (int j = 1; j < n; j++) {
            int count = 0;
            for (int[] ints : grid) {
                if (ints[j] == 0) {
                    count++;
                }
            }
            if (count > m / 2) {
                for (int i = 0; i < m; i++) {
                    grid[i][j] ^= 1;
                }
            }
            ans = Math.max(ans, getSum(grid));
        }
        return ans;
    }

    public int getSum(int[][] grid) {
        int n = grid[0].length;
        int sum = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                sum += (ints[j] << (n - j - 1));
            }
        }
        return sum;
    }
}
