package bytedance.背包问题;

public class 组合总数 {
    // 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
    // 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
    public int combinationSum4(int[] nums, int target) {
        // 组合dp
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }
}
