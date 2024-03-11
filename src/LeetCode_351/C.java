package LeetCode_351;

import java.util.ArrayList;
import java.util.List;

public class C {
    private static final int MOD = 1000000007;
    public int numberOfGoodSubarraySplits(int[] nums) {
        long ans = 1L;
        // calculate the number of "0" between two "1"
        List<Integer> list = new ArrayList<>();
        int first = -1;
        int second = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                    list.add(second - first);
                } else {
                    first = second;
                    second = i;
                    list.add(second - first);
                }
            }
        }
        if (first == -1) {
            return 0;
        }
        for (int num : list) {
            ans = (ans * num) % MOD;
        }
        return (int)(ans % MOD);
    }
}
