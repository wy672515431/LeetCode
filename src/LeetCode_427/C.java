package LeetCode_427;

import java.util.HashSet;
import java.util.Set;

public class C {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        Set<Integer> set = new HashSet<>();
        long ans = Long.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            for (int j = k; i + j - 1 < n; j += k) {
                sum = preSum[i + j] - preSum[i];
                ans = Math.max(ans, sum);
                if (sum < 0) {
                    break;
                }
                set.add(i + j - k);
            }
        }
        return ans;
    }
}
