package LeetCode.BinarySearch;

import java.util.Arrays;

public class _1283 {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 0;
        int high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (check(nums, mid, threshold)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public boolean check(int[] nums, int divider, int threshold) {
        int sum = 0;
        for (int num : nums) {
            sum += (num % divider == 0) ? num / divider : (num / divider  + 1);
        }
        if (sum <= threshold) {
            return true;
        } else {
            return false;
        }
    }
}
