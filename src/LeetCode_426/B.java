package LeetCode_426;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class B {
    public int getLargestOutlier(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if ((sum - nums[i]) % 2 != 0) {
                continue;
            }
            int special = (sum - nums[i]) / 2;
            if (special == nums[i]) {
                if (map.get(special) > 1) {
                    res = Math.max(res, nums[i]);
                }
            } else {
                if (map.containsKey(special)) {
                    res = Math.max(res, nums[i]);
                }
            }
        }
        return res;
    }
}
