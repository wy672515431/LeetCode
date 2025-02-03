package LeetCode_423;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C {
    private static final int MOD = 1000000007;
    public int sumOfGoodSubsequences(int[] nums) {
        int n = nums.length;
        Map<Integer, Long> dp = new HashMap<>();
        Map<Integer, Long> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long sum = nums[i];
            long count = 1;
            if (dp.containsKey(nums[i] - 1)) {
                sum = (sum + dp.get(nums[i] - 1) + nums[i] * cnt.get(nums[i] - 1)) % MOD;
                count = (count + cnt.get(nums[i] - 1)) % MOD;
            }
            if (dp.containsKey(nums[i] + 1)) {
                sum = (sum + dp.get(nums[i] + 1) + nums[i] * cnt.get(nums[i] + 1)) % MOD;
                count = (count + cnt.get(nums[i] + 1)) % MOD;
            }
            dp.put(nums[i], dp.getOrDefault(nums[i], 0L) + sum);
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0L) + count);
        }
        long res = 0;
        for (long value : dp.values()) {
            res = (res + value) % MOD;
        }
        return (int) res;
    }
}
