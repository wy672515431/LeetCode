package LeetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组nums，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 */
public class 删除并获得点数 {
    // 1 1 2 3 3 4
    // dp[i][1] 选择第i个数字代表前i个数字得到的最大分数
    // dp[i][0] 不选择第i个数字
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        //如果
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                dp[i][1] = dp[i - 1][0] + nums[i];
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            } else if (nums[i] == nums[i - 1]) {
                dp[i][1] = dp[i - 1][1] + nums[i];
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0]) + nums[i];
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            }
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}
