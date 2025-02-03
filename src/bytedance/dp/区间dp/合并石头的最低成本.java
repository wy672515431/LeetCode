package bytedance.dp.区间dp;

public class 合并石头的最低成本 {
    private static final int INF = 0x3f3f3f3f;
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        // 每次减少k - 1个，最终减少k - 1个
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        int[][] dp = new int[n][n];
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }
        // dp[i][j]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = INF;
                for (int x = i; x < j; x += k - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][x] + dp[x + 1][j]);
                }
                if ((j - i) % (k - 1) == 0) {
                    dp[i][j] += preSum[j + 1] - preSum[i];
                }
            }
        }
        return dp[0][n - 1];
    }
}
