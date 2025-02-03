package LeetCode_423;

import java.util.Arrays;
import java.util.List;

public class B {
    int[] dp;
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int low = 1, high = n / 2;
        int ans = 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (check(nums, mid)) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(List<Integer> nums, int k) {
        int n =  nums.size();
        for (int i = 0, j = i + k; i + k - 1 < n && j + k - 1 < n; i++, j++) {
            if (dp[i + k - 1] >= k && dp[j + k - 1] >= k) {
                return true;
            }
        }
        return false;
    }
}
