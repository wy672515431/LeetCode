package LeetCode_356;

import LeetCode.ArrayShuffle;

import java.util.Arrays;

public class A {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        return (int)Arrays.stream(hours).filter(o -> o >= target).count();
    }
}
