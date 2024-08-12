package LeetCode_408;

import java.util.Arrays;

public class A {
    public boolean canAliceWin(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        sum1 = Arrays.stream(nums).filter(i -> String.valueOf(i).length() == 1).sum();
        sum2 = Arrays.stream(nums).filter(i -> String.valueOf(i).length() == 2).sum();
        return sum1 != sum2;
    }
}
