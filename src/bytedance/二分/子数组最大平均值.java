package bytedance.二分;

import java.util.Arrays;

public class 子数组最大平均值 {
    private static final double eps = 1e-6;

    public double findMaxAverage(int[] nums, int k) {
        double low = Arrays.stream(nums).min().orElseThrow();
        double high = Arrays.stream(nums).max().orElseThrow();
        while (high - low > eps) {
            double mid = (high - low) / 2 + low;
            if (check(nums, mid, k)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;

    }

    private boolean check(int[] nums, double avg, int k) {
        // 每一项 - avg 的和大于等于0，说明存在子数组的平均值大于等于 avg
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i] - avg;
        }
        if (sum >= 0) {
            return true;
        }
        // sum - [0.. i]
        // preSum - [0.. i - k]
        // minSum - min([0 .. j]) j <= i - k
        double preSum = 0;
        // 可能为空
        double minSum = 0;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - avg;
            // 满足
            preSum += nums[i - k] - avg;
            minSum = Math.min(preSum, minSum);
            if (sum - minSum >= 0) {
                return true;
            }
        }
        return false;
    }
}
