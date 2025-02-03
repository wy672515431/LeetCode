package bytedance.dp.状态压缩dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 分配重复整数 {
    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> counts = map.values().stream().toList();
        int n = counts.size();
        int m = quantity.length;
        int[] cost = new int[1 << m];
        for (int i = 0; i < (1 << m); i++) {
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    cost[i] += quantity[j];
                }
            }
        }
        // dp[i][j] 表示前 i 个数是否能够分配成 j 这个状态
        boolean[][] dp = new boolean[n + 1][1 << m];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < (1 << m); j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                    continue;
                }
                for (int sub = j; sub != 0 && !dp[i][j]; sub = (sub - 1) & j) {
                    if (cost[sub] <= counts.get(i - 1)) {
                        dp[i][j] |= dp[i - 1][sub ^ j];
                    }
                }
            }
        }
        return dp[n][(1 << m) - 1];
    }
}
