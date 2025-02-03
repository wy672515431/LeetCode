package LeetCode_145_Double;

import java.util.Arrays;

public class A {
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                if (nums[i] > k) {
                    ans++;
                } else if (nums[i] < k) {
                    return -1;
                }
            } else {
                if (nums[i] == nums[i - 1]) {
                    continue;
                }
                if (nums[i] >= k) {
                    ans++;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
}
