package LeetCode_91_Double;

import java.util.Arrays;
import java.util.HashSet;

public class A {
    public int distinctAverages(int[] nums) {
        HashSet<Double> set = new HashSet<>();
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while (i < j) {
            set.add((nums[i] + nums[j]) / 2.0);
            i++;
            j--;
        }
        return set.size();
    }
}
