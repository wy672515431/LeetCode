package bytedance.数组;

public class 最大子数组和 {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果前面的和小于0，直接扔掉
            if (sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            if (sum > ans) {
                ans = sum;
            }
        }
        return ans;
    }
}
