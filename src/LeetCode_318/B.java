package LeetCode_318;

import java.util.HashMap;

public class B {
    public long maximumSubarraySum(int[] nums, int k) {
        long ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // init
        long sum = 0;
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            sum += nums[i];
        }
        if (map.size() == k) {
            ans = Math.max(ans, sum);
        }
        // windows
        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            map.put(nums[i - k], map.getOrDefault(nums[i -k], 1) - 1);
            if (map.get(nums[i - k]) == 0) {
                map.remove(nums[i - k]);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            sum += nums[i];
            if (map.size() == k) {
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }
}
