package bytedance.dp;

public class 最大正方形 {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // dp[i][j]代表以(i, j)为右下角正方形的最大面积
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = matrix[i][j];
                if (ch == '1') {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j])) + 1;
                    ans = Math.max(ans, dp[i + 1][j + 1] * dp[i + 1][j + 1]);
                }
            }
        }
        return ans;
    }
}
