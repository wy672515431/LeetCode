package LeetCode_300;

public class C {
    //第n天知道秘密的人数 = 第n - 1天知道秘密的人数 - (n - foreget)天新知道秘密的人数 + (n - forget + 1) ~ (n - delay) 新知道秘密的人数
    //第n天新知道秘密的人数 
    static final int mod = 1000000007;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[][] dp = new int[n + 1][2];
        //第1天知道秘密的人数为1，新知道秘密的人数为1
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i - forget + 1; j <= i - delay ; j++) {
                if (j <= 0) {
                    continue;
                }
                dp[i][1] = (dp[i][1] + dp[j][1]) % mod;
            }
            dp[i][0] = (dp[i][0] + dp[i - 1][0] + dp[i][1]) % mod;
            if (i - forget > 0) {
                dp[i][0] = (dp[i][0] - dp[i - forget][1]) % mod;
            }
        }
        return (dp[n][0] + mod) % mod;
    }
}
