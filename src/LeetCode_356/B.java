package LeetCode_356;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class B {
    public static int countCompleteSubarrays(int[] nums) {
        // dp[i][j]表示以i为起点，以j为终点的数组是否为完全子数组
        int len = nums.length;
        boolean[][] dp = new boolean[len][len];
        HashMap<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i != j) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
                int num = nums[j];
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (map.keySet().size() == set.size()) {
                    dp[i][j] = true;
                }
            }
            map.clear();
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (dp[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 2};
        countCompleteSubarrays(nums);
    }
}
