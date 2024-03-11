package bytedance.背包问题;

import java.util.Arrays;

public class 分割等和子集 {
    public boolean canPartition(int[] nums) {
        // 0-1背包存在问题
        int sum = Arrays.stream(nums).sum();
        // 特殊情况
        if (sum % 2 != 0) {
            return false;
        }
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = sum / 2; j >= num; j--) {
                dp[j] = dp[j] | dp[j - num];
            }
        }
        return dp[sum / 2];
    }
}
