package LeetCode;

import java.util.HashMap;

public class 和等于k的最长子数组长度 {
    public int maxSubArrayLen(int[] nums, int k) {
        int ans = 0;
        int length = nums.length;
        int[] presum = new int[length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            presum[i + 1] = presum[i] + nums[i];
            map.put(presum[i + 1], i + 1);
        }
        // 对于一个presum[i] ，我们要找到presum[x] - presum[i] = k
        // length = x - i
        for (int i = 0; i <= length; i++) {
            if (map.get(presum[i] + k) != null && map.get(presum[i] + k) > i) {
                ans = Math.max(ans, map.get(presum[i] + k) - i);
            }
        }
        return ans;
    }
}
