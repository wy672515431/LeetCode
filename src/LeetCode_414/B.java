package LeetCode_414;

import java.util.Arrays;

public class B {
    public int maxPossibleScore(int[] start, int d) {
        int n = start.length;
        Arrays.sort(start);
        long low = 0, high = start[n - 1] + d - start[0];
        long ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(start, d, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int)ans;
    }

    private boolean check(int[] start, int d, long mid) {
        int i = 0;
        long first = start[i];
        while (i < start.length - 1) {
            if (first + mid < start[i + 1]) {
                i++;
                first = start[i];
            } else if (first + mid > start[i + 1] + d) {
                return false;
            } else {
                first = first + mid;
                i++;
            }
        }
        return true;
    }
}
