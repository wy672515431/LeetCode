package LeetCode;

import java.util.Arrays;

public class 最长递增子序列 {
    //dp[i]代表前i个的最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public int lengthOfLIS1 (int[] nums) {
        int[] dp = new int[nums.length];
        int maxLength = 1;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > dp[maxLength - 1]) {
                maxLength++;
                dp[maxLength - 1] = nums[i];
            } else {
                int index = lowerBound(dp, nums[i], maxLength);
                dp[index] = nums[i];
            }
        }
        return maxLength;
    }
    /**
     * 找到数组中比target大于等于的第一个坐标
     * @param nums
     * @param target
     * @return
     */
    private int lowerBound(int[] nums, int target, int maxLength) {
        int left = 0;
        int right = maxLength - 1;
        int mid;
        while (left < right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
