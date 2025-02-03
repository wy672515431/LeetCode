package LeetCode_416;

import java.util.Arrays;

public class B {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int maxTime = Arrays.stream(workerTimes).max().getAsInt();
        long low = 0, high = (long)maxTime * (mountainHeight + 1) * mountainHeight / 2;
        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(mountainHeight, workerTimes, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean check(int mountainHeight, int[] workerTimes, long time) {
        long workHeight = 0;
        for (int workerTime : workerTimes) {
            // workTime * (1 + 2 + 3 + n ) <= time
            // workTime * n * (n + 1) / 2 <= time
            // n * (n + 1) / 2 <= time / workTime
            workHeight += (long) ((-1 + Math.sqrt(1 + 8 * time / workerTime)) / 2);
        }
        return workHeight >= mountainHeight;
    }
}
