package bytedance.dp;

import java.util.Arrays;

public class 最小数组和 {
    /**
     *
     * @param nums
     * @param k
     * @param op1 (x + 1) / 2
     * @param op2 x - k (x >= k)
     * @return
     */
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][op1 + 1][op2 + 1];
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= op1; j++) {
                for (int l = 0; l <= op2; l++) {
                    dp[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][op1][op2] = sum;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= op1; j++) {
                for (int l = 0; l <= op2; l++) {
                    if (dp[i][j][l] == Integer.MAX_VALUE) {
                        continue;
                    }
                    // do nothing
                    dp[i + 1][j][l] = Math.min(dp[i + 1][j][l], dp[i][j][l]);
                    // only op1
                    if (j > 0) {
                        dp[i + 1][j - 1][l] = Math.min(dp[i + 1][j - 1][l], dp[i][j][l] - nums[i] + (nums[i] + 1) / 2);
                    }
                    // only op2
                    if (l > 0 && nums[i] >= k) {
                        dp[i + 1][j][l - 1] = Math.min(dp[i + 1][j][l - 1], dp[i][j][l] - k);
                    }
                    // op1 and op2
                    if (j > 0 && l > 0) {
                        if ((nums[i] + 1) / 2 >= k) {
                            dp[i + 1][j - 1][l - 1] = Math.min(dp[i + 1][j - 1][l - 1], dp[i][j][l] - nums[i] + (nums[i] + 1) / 2 - k);
                        } else if (nums[i] >= k) {
                            dp[i + 1][j - 1][l - 1] = Math.min(dp[i + 1][j - 1][l - 1], dp[i][j][l] - nums[i] + (nums[i] - k + 1) / 2);
                        }
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j <= op1; j++) {
            for (int l = 0; l <= op2; l++) {
                res = Math.min(res, dp[n][j][l]);
            }
        }
        return res;
    }
}
