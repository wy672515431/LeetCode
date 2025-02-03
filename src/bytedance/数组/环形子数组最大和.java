package bytedance.数组;

public class 环形子数组最大和 {
    public int maxSubarraySumCircular(int[] nums) {
        // 存在两种情况: 1. 最大子数组不跨越数组首尾，2. 最大子数组跨越数组首尾
        // curMax 代表不跨越数组首尾的最大子数组和，curMin 代表不跨越数组首尾的最小子数组和
        // 跨越数组首尾的最大子数组和 = 数组总和 - 不跨越数组首尾的最小子数组和
        int total = 0, maxSum = nums[0], curMax = 0, minSum = nums[0], curMin = 0;
        for (int a : nums) {
            // curMax < 0, 对于后面的子数组和没有增益，直接舍弃
            // curMax + a < a 说明curMax < 0
            curMax = Math.max(curMax + a, a);
            // 更新全局最大值
            maxSum = Math.max(maxSum, curMax);
            // curMax > 0 对于后面的子数组和没有增益,直接舍弃
            // curMin + a > a 说明curMin > 0
            curMin = Math.min(curMin + a, a);
            //更新全局最小值
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}
