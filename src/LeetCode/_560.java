package LeetCode;

import java.util.HashMap;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 */
public class _560 {
    public int subarraySum(int[] nums, int k) {
        //sum[i] - sum[j] = k sum[i] = sum[j] + k  i > j
        int ans = 0;
        HashMap<Integer, Integer> mMap = new HashMap();
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        mMap.put(sum[0], 1);
        for(int i = 1; i < nums.length; i++) {
            mMap.getOrDefault(sum[i] - k, 0);
            if(mMap.get(sum[i]) != null) {
                mMap.put(sum[i], mMap.get(sum[i]) + 1);
            }else {
                mMap.put(sum[i], 1);
            }
        }
        return ans;
    }
}
