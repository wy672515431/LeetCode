package bytedance.dp;

import java.util.Arrays;

public class 最长递增子序列 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int optimizedLengthOfLIS(int[] nums) {
        int ans = 1;
        int n = nums.length;
        // 表示长度为 i 的最长上升子序列的末尾元素的最小值
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > dp[ans - 1]) {
                ans++;
                dp[ans - 1] = nums[i];
            } else {
                int index = lowerBound(dp, nums[i], ans);
                dp[index] = nums[i];
            }
        }
        return ans;
    }

    /**
     * 返回数组中大于等于target的第一个下标
     * @param nums
     * @param target
     * @return
     */
    public int lowerBound(int[] nums, int target, int maxLength) {
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
