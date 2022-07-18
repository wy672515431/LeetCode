package LeetCode_302;

import java.util.HashMap;
import java.util.Map;

public class A {
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], cnt + 1);
        }
        int pair = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            pair += val / 2;
        }
        ans[0] = pair;
        ans[1] = n - pair * 2;
        return ans;
    }
}
