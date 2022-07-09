package LeetCode;

public class 环形子数组最大和 {
    public int maxSubarraySumCircular(int[] nums) {
        //环形。
        //如果对于首尾区间最大，则最大值 = 数组和 - 区间内最小值
        int total = 0, maxSum = nums[0], curMax = 0, minSum = nums[0], curMin = 0;
        for (int a : nums) {
            //如果curMax < 0,则当前最大值为a。反之如果curMax大于等于0，当前最大值为 curMax + a;
            curMax = Math.max(curMax + a, a);
            //更新全局最大值
            maxSum = Math.max(maxSum, curMax);
            //如果当前curMin > 0 ,则当前最小值为a，如果小于0，则为curMin + a;
            curMin = Math.min(curMin + a, a);
            //更新全局最小值
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}
