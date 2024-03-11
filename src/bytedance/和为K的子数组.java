package bytedance;

import java.util.HashMap;

public class 和为K的子数组 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        // init
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        // [l .. r]  preSum[r + 1] - preSum[l] == k r >= l
        for (int i = 0; i < n; i++) {
            ans += map.getOrDefault(preSum[i + 1] - k, 0);
            map.put(preSum[i + 1], map.getOrDefault(preSum[i + 1], 0) + 1);
        }
        return ans;
    }
}
