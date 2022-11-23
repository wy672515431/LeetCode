package LeetCode;

import java.util.Arrays;

public class 子序列宽度之和 {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * 一个序列的宽度定义为序列中最大元素和最小元素的差值
     * 排序并不影响结果
     * 对于nums[i]和nums[j](i < j)，以(nums[j] - nums[i])为宽度的子序列个数为 2^(j - i - 1)
     * Aj代表以j为最大值的序列的宽度之和，Aj = nums[j] * (yj - 1) - xj
     * yj+1 = 2 * yj, xj+1 = 2 * xj + nums[j]
     * 初始化 x0 = nums[0], y0 = 2
     * @param nums
     * @return
     */
    public int sumSubseqWidths(int[] nums) {
        long ans = 0;
        Arrays.sort(nums);
        long x = nums[0], y = 2;
        for (int i = 1; i < nums.length; i++) {
            ans = (ans + nums[i] * (y - 1) - x) % MOD;
            y = (y * 2) % MOD;
            x = (2 * x + nums[i]) % MOD;
        }
        return (int)((ans + MOD) % MOD);
    }
}
