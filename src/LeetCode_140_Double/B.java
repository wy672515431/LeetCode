package LeetCode_140_Double;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class B {
    public long maximumTotalSum(int[] maximumHeight) {
        long ans = 0L;
        int n = maximumHeight.length;
        Set<Integer> set = new HashSet<>();
        Arrays.sort(maximumHeight);
        // 当前集合中的最小值
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (!set.contains(maximumHeight[i])) {
                ans += maximumHeight[i];
                set.add(maximumHeight[i]);
                min = Math.min(min, maximumHeight[i]);
            } else {
                if (min == 1) {
                    return -1;
                }
                ans += min - 1;
                set.add(min - 1);
                min = min - 1;
            }
        }
        return ans;
    }
}
