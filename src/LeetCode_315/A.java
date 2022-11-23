package LeetCode_315;

import java.util.HashSet;

public class A {
    public int findMaxK(int[] nums) {
        int ans = -1;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (num > 0 && set.contains(-num)) {
                ans = Math.max(ans, num);
            }
        }
        return ans;
    }
}
