package bytedance.dp.区间dp;

import java.util.Arrays;

public class 切棍子的最小成本 {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        newCuts[0] = 0;
        newCuts[m + 1] = n;
        System.arraycopy(cuts, 0, newCuts, 1, m);

        Arrays.sort(newCuts);
        int[][] dp = new int[m + 2][m + 2];
        // k > i && k < j
        // dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + newCuts[j] - newCuts[i]);
        for (int i = m + 1; i >= 0; i--) {
            for (int j = i + 2; j < m + 2; j++) {
                dp[i][j] = 0x3f3f3f3f;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + newCuts[j] - newCuts[i]);
                }
            }
        }
        return dp[0][m + 1];
    }
}
