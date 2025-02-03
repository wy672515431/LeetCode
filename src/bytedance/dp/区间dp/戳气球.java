package bytedance.dp.区间dp;

public class 戳气球 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        newNums[0] = 1;
        newNums[n + 1] = 1;
        System.arraycopy(nums, 0, newNums, 1, n);
        int[][] dp = new int[n + 2][n + 2];
        // dp[i][j] = Math.max(dp[i, j], dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j])
        for (int i = n + 1; i >= 0; i--) {
            for (int j = i + 2; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
        return dp[0][n + 1];
    }
}
