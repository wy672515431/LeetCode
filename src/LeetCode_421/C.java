package LeetCode_421;

import java.util.Arrays;

public class C {
    private static final int MOD = 1000000007;
    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int MAX = Arrays.stream(nums).max().getAsInt();
        int[][][] dp = new int[n + 1][MAX + 1][MAX + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= MAX; j++) {
                for (int k = 0; k <= MAX; k++) {
                    // 放到第一个序列
                    dp[i][gcd(j, num)][k] = (dp[i][gcd(j, num)][k] + dp[i - 1][j][k]) % MOD;
                    // 放到第二个序列
                    dp[i][j][gcd(k, num)] = (dp[i][j][gcd(k, num)] + dp[i - 1][j][k]) % MOD;
                    // 不放
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k]) % MOD;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= MAX; i++) {
            ans = (ans + dp[n][i][i]) % MOD;
        }
        return (int) ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
