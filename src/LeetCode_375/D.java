package LeetCode_375;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class D {
    private static final int MOD = (int)(1e9) + 7;
    public static int numberOfGoodPartitions(int[] nums) {
        Map<Integer, Interval> map = new HashMap<>();
        int n = nums.length;
        // get the interval
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new Interval(i, i));
            } else {
                Interval interval = map.get(nums[i]);
                interval.end = i;
            }
        }
        Interval[] intervals = map.values().toArray(new Interval[0]);
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });
        int count = 0;
        // cal the count
        Interval preInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            Interval curInterval = intervals[i];
            // check if the two intervals overlap
            if (curInterval.start >= preInterval.end) {
                count++;
                preInterval = curInterval;
            } else {
                preInterval.end = Math.max(preInterval.end, curInterval.end);
            }
        }
        count += 1;
        int ans = 1;
        for (int i = 0; i < count; i++) {
            ans = (ans * 2) % MOD;
        }
        return ans;
    }

    static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        D.numberOfGoodPartitions(new int[] {1, 2, 3, 4});
    }
}
