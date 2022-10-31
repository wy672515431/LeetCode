package LeetCode.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 找到k个最接近的元素 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int higherIndex = upperBound(arr, x);
        int lowerIndex = higherIndex - 1;
        while (k > 0) {
            int higherNum = (higherIndex >= arr.length) ? 0x3f3f3f3f : arr[higherIndex];
            int lowerNum = (lowerIndex < 0) ? 0x3f3f3f3f : arr[lowerIndex];
            if (Math.abs(x - lowerNum) <= Math.abs(higherNum - x)) {
                ans.add(arr[lowerIndex--]);
            } else {
                ans.add(arr[higherIndex++]);
            }
            k--;
        }
        Collections.sort(ans);
        return ans;
    }

    //找到大于x的第一个数
    public int upperBound(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int mid;
        if (x >= arr[high]) {
            return high + 1;
        }
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (arr[mid] <= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
