package bytedance.dp;

public class 不相交的握手 {
    private static final int MOD = (int) 1e9 + 7;
    public int numberOfWays(int numPeople) {
        int[] dp = new int[numPeople + 1];
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i <= numPeople; i += 2) {
            for (int j = 0; j < i; j += 2) {
                dp[i] = (dp[i] + (int)((long)dp[j] * dp[i - j - 2] % MOD)) % MOD;
            }
        }
        return dp[numPeople];
    }
}
