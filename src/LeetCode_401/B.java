package LeetCode_401;

public class B {
    private static final int MOD = 1000000007;
    public int valueAfterKSeconds(int n, int k) {
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                dp[j][i] = dp[j][i - 1];
                dp[j][i] = (dp[j][i] + dp[j - 1][i]) % MOD;
            }
        }
        return dp[n - 1][k];
    }
}
