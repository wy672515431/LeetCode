package LeetCode_137_Double;

import java.util.ArrayDeque;
import java.util.Deque;

public class A {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            boolean flag = true;
            for (int j = 1; j < k; j++) {
                if (nums[i + j] != nums[i + j - 1] + 1) {
                    ans[i] = -1;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans[i] = nums[i + k - 1];
            }
        }
        return ans;
    }
}
