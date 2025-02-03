package LeetCode_423;

import java.util.List;

public class A {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n =  nums.size();
        for (int i = 0, j = i + k; i + k - 1 < n && j + k - 1 < n; i++, j++) {
            int x;
            for (x = 1; x < k; x++) {
                if (nums.get(i + x) <= nums.get(i + x - 1) || nums.get(j + x) <= nums.get(j + x - 1)) {
                    break;
                }
            }
            if (x == k) {
                return true;
            }
        }
        return false;
    }
}
