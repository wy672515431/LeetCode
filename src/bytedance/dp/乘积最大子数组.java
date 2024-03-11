package bytedance.dp;

public class 乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = nums[i];
            dp[i][1] = nums[i];
        }
        for (int i = 1; i < n; i++) {
            // 如果nums是正数
            if (nums[i] > 0) {
                dp[i][0] = Math.max(dp[i - 1][0] * nums[i], dp[i][0]);
                dp[i][1] = Math.min(dp[i - 1][1] * nums[i], dp[i][0]);
            } else {
                dp[i][1] = Math.min(dp[i - 1][0] * nums[i], dp[i][0]);
                dp[i][0] = Math.max(dp[i - 1][1] * nums[i], dp[i][0]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i][0]);
        }
        return ans;
    }
}
