package LeetCode_410;

public class C {
    private static final int MOD = 1000000007;

    public int countOfPairs(int[] nums) {
        int n = nums.length;
        // 考虑 i 和 i + 1
        // 如果nums[i + 1] > nums[i]
        // 为了保证arr1单调非递减和arr2单调非递增，arr1[i + 1] >= arr1[i] + nums[i + 1] - nums[i]
        // arr1[i] = j
        long[][] dp = new long[n][1005];
        long[] sum = new long[1005];
        for (int i = 0; i <= nums[0]; i++) {
            // 前缀和
            dp[0][i] = 1;
            sum[i + 1] = (sum[i] + dp[0][i]) % MOD;
        }
        for (int i = 1; i < n; i++) {
            // 前面的取值在[0, nums[i - 1]]区间范围
            if (nums[i] >= nums[i - 1]) {
//                for (int j = nums[i] - nums[i - 1]; j <= nums[i]; j++) {
//                    for (int k = 0; k <= nums[i - 1]; k++) {
//                        if (k + j <= nums[i]) {
//                            dp[i][k + j] = (dp[i][k + j] + dp[i - 1][k]);
//                        }
//                    }
//                }
                  for (int j = 0; j <= nums[i]; j++) {
                      if (j < nums[i] - nums[i - 1]) {
                          continue;
                      }
                      dp[i][j] = (dp[i][j] + sum[j - (nums[i] - nums[i - 1]) + 1]) % MOD;
                  }
            } else {
//                for (int j = 0; j <= nums[i]; j++) {
//                    for (int k = 0; k <= nums[i - 1]; k++) {
//                        if (j >= k) {
//                            dp[i][j] = (dp[i][j] + dp[i - 1][k]);
//                        }
//                    }
//                }
                for (int j = 0; j <= nums[i]; j++) {
                    dp[i][j] = (dp[i][j] + sum[j + 1]) % MOD;
                }
            }
            // 更新sum
            for (int j = 0; j <= nums[i]; j++) {
                sum[j + 1] = (sum[j] + dp[i][j]) % MOD;
            }
        }
        long res = 0;
        for (int i = 0; i <= nums[n - 1]; i++) {
            res = (res + dp[n - 1][i]) % MOD;
        }
        return (int)(res % MOD);
    }
}
