package LeetCode_144_Double;

import java.util.Arrays;

public class D {
    public int maxCollectedFruits(int[][] fruits) {
        // A只能走对角线
        int n = fruits.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][n - 1] = fruits[0][n - 1];
        // 对角线上 i < j
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + fruits[i][j];
                if (j + 1 < n) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + 1] + fruits[i][j]);
                }
            }
        }
        ans += dp[n - 2][n - 1];

        dp[n - 1][0] = fruits[n - 1][0];
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i][j - 1]) + fruits[i][j];
                if (i + 1 < n) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + fruits[i][j]);
                }
            }
        }
        ans += dp[n - 1][n - 2];
        return ans;
    }
}
