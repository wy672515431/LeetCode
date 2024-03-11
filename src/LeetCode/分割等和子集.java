package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class 分割等和子集 {
    // 背包问题，选出的东西恰为数组和的一半
    public boolean canPartition(int[] nums) {
        // special cases
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        // 背包问题 dp[i][j]代表从[0, i]选若干个整数，是否能够恰好和为j.
        // dp[i][0] = true
        // dp[0][nums[0]] = true
        // 当前i取还是不取
        // j > nums[i]
        // dp[i][j] = dp[i - 1][j - nums[i]] | dp[i - 1][j]
        // dp[i][j] = dp[i - 1][j]
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
