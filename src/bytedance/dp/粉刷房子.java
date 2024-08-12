package bytedance.dp;

public class 粉刷房子 {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        // 前面最小的下标
        int min = -1;
        // 前面第二小的下标
        int secondMin = -1;
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
            if (min == -1) {
                min = i;
            } else if (secondMin == -1) {
                if (dp[0][min] >= dp[0][i]) {
                    secondMin = min;
                    min = i;
                } else {
                    secondMin = i;
                }
            } else {
                if (dp[0][i] < dp[0][min]) {
                    secondMin = min;
                    min = i;
                } else if (dp[0][i] < dp[0][secondMin]) {
                    secondMin = i;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (j == min) {
                    dp[i][j] = dp[i - 1][secondMin] + costs[i][j];
                } else {
                    dp[i][j] = dp[i - 1][min] + costs[i][j];
                }
            }
            min = -1;
            secondMin = -1;
            for (int j = 0; j < k; j++) {
                if (min == -1) {
                    min = j;
                } else if (secondMin == -1) {
                    if (dp[i][min] >= dp[i][j]) {
                        secondMin = min;
                        min = j;
                    } else {
                        secondMin = j;
                    }
                } else {
                    if (dp[i][j] < dp[i][min]) {
                        secondMin = min;
                        min = j;
                    } else if (dp[i][j] < dp[i][secondMin]) {
                        secondMin = j;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }
}
