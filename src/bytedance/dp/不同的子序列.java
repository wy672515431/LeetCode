package bytedance.dp;

public class 不同的子序列 {
    private static final int MOD = (int)1e9 + 7;

    /**
     * 在s中找到t的不同子序列的个数
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        int[][] dp = new int[tlen + 1][slen + 1];
        for (int i = 0; i <= slen; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < tlen; i++) {
            char ch1 = t.charAt(i);
            for (int j = 0; j < slen; j++) {
                char ch2 = s.charAt(j);
                if (ch1 == ch2) {
                    dp[i + 1][j + 1] = (dp[i][j] + dp[i + 1][j]) % MOD;
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                }
            }
        }
        return dp[tlen][slen];
    }
}
