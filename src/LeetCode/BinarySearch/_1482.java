package LeetCode.BinarySearch;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class _1482 {
    /**
     *
     * @param bloomDay
     * @param m m束花
     * @param k 每束花消耗相邻的k朵花
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int low = 1;
        int high = 0;
        for (int day : bloomDay) {
            high = Math.max(high, day);
        }
        int mid;
        if (m * k > bloomDay.length) {
            return -1;
        }
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (check(bloomDay, mid, m, k)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public boolean check(int[] bloomDay, int endDay, int m, int k) {
        int numOfBunchFlowers = 0;
        int numOfOneBunch = 0;
        for (int day : bloomDay) {
            if (day <= endDay) {
                numOfOneBunch++;
                if (numOfOneBunch == k) {
                    numOfBunchFlowers++;
                    numOfOneBunch = 0;
                }
            } else {
                numOfOneBunch = 0;
            }
        }
        return numOfBunchFlowers >= m;
    }
}
