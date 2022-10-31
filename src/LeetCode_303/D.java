package LeetCode_303;

import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class D {
    /**
     * And -----两个数共同1的个数 
     * Or  -----两个数1的个数 - 两个数共同1的个数
     * 两个数一的个数
     * @param nums
     * @param k
     * @return
     */
    public long countExcellentPairs(int[] nums, int k) {
        long ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int[] cnt = new int[set.size()];
        int count = 0;
        for (Integer e : set) {
            cnt[count++] = Integer.bitCount(e);
        }
        Arrays.sort(cnt);
        for (Integer e : set) {
            ans += cnt.length - lowerBound(cnt,  k - Integer.bitCount(e));
        }
        return ans;
    }

    public int lowerBound(int[] cnt, int target) {
        int low = 0;
        int high = cnt.length - 1;
        int mid;
        if (target > cnt[high]) {
            return high + 1;
        }
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (cnt[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
