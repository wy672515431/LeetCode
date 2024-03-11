package LeetCode_328;

import java.util.Arrays;

public class A {
    public int differenceOfSum(int[] nums) {
        int sum1 = Arrays.stream(nums).sum();
        int sum2 = 0;
        for (int num : nums) {
            while (num != 0) {
                sum2 += num % 10;
                num /= 10;
            }
        }
        return sum1 - sum2;
    }
}
