package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class 拆分成最多数目的正偶数之和 {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        // if final sum is odd, return empty list
        if (finalSum % 2 == 1) {
            return ans;
        }
        // if final sum is even, we can always split it into two even numbers. But each number should be unique.
        // So we can split it into 2, 4, 6, 8, ... , 2 * n

        // (1 + n) * n = finalSum n^2 + n = finalSum
        int n = (int) ((-1 + Math.sqrt(1 + 4 * finalSum)) / 2);
        long start = 2L;
        long sum = 0L;
        for (int i = 1; i < n; i++) {
            ans.add(start);
            sum += start;
            start += 2L;
        }
        ans.add(finalSum - sum);
        return ans;
    }
}
