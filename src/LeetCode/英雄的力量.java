package LeetCode;

import java.util.Arrays;

public class 英雄的力量 {
    private static final int MOD = 1000000007;
    public static int sumOfPower(int[] nums) {
        long ans = 0L;
        Arrays.sort(nums);
        for (long num : nums) {
            long mult = (num * ((num * num) % MOD)) % MOD;
            ans = (ans + mult) % MOD;
        }
        // 最小值是nums[i]，最大值是nums[j](j > i)
        // sum = nums[i] * Math.pow(2, j - i - 1) * nums[j] * nums[j]
        int len = nums.length;
        long[] pre = new long[len + 1];
        for (int i = 1; i < len; i++) {
            pre[i + 1] = ((2 * pre[i]) % MOD + nums[i - 1]) % MOD;
        }
        for (int i = 1; i < len; i++) {
            ans = (ans + ((((long)nums[i] * nums[i]) % MOD) * (pre[i + 1])) % MOD) % MOD;
        }
        return (int)(ans % MOD);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        System.out.println(sumOfPower(nums));
    }
}
