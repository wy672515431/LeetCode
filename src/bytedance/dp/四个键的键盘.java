package bytedance.dp;

public class 四个键的键盘 {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 1; j < i - 2; j++) {
                // j + 1 Ctrl-A
                // j + 2 Ctrl-C
                dp[i] = Math.max(dp[i], dp[j] + (i - j - 2) * dp[j]);
            }
        }
        return dp[n];
    }
}
