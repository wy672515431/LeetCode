package data_structure;

import java.util.List;

public class 区间和的个数 {
    /**
     * @param nums the input array
     * @param lower the lower bound of range sum
     * @param upper the upper bound of range sum
     * @return return the numbers of range sum that in [lower, upper]
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] preSum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return mergeSort(preSum, lower, upper, 0, len - 1);
    }

    public int mergeSort(long[] preSum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int sum1 = mergeSort(preSum, lower, upper, left, mid);
        int sum2 = mergeSort(preSum, lower, upper, mid + 1, right);
        int ans = sum1 + sum2;
        int i = left, l = mid + 1, r = mid + 1;
        // preSum[j] - preSum[i] >= lower && preSum[j] - preSum[i] <= upper
        // we need to compute the number of (i, j)
        // we assume the n1,n2 is an increasing sequence
        // what we should do to compute the number of (i, j) that satisfy n1[i] - n2[j] >= lower && n1[i] - n2[j] <= upper
        // we use two pointers l and r to compute.
        while (i <= mid) {
            while (l <= right && preSum[l] - preSum[i] < lower) {
                l++;
            }
            while (r <= right && preSum[r] - preSum[i] <= upper) {
                r++;
            }
            ans += r - l;
            i++;
        }
        long[] sorted = new long[right - left + 1];
        int p1 = left, p2 = mid + 1, p = 0;
        while (p1 <= mid || p2 <= right) {
            if (p1 > mid) {
                sorted[p++] = preSum[p2++];
            } else if (p2 > right) {
                sorted[p++] = preSum[p1++];
            } else {
                if (preSum[p1] < preSum[p2]) {
                    sorted[p++] = preSum[p1++];
                } else {
                    sorted[p++]  = preSum[p2++];
                }
            }
        }
        for (int j = 0; j < sorted.length; j++) {
            preSum[left + j] = sorted[j];
        }
        return ans;
     }
}
