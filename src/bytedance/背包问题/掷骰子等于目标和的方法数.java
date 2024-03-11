package bytedance.背包问题;

public class 掷骰子等于目标和的方法数 {
    //分组背包，有n个组，每个组只能选一次，每个组有多个物品(k)，问填满容量为target的背包有几种方式
    private static final int MOD = (int)1e9 + 7;
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        // 当 n = 1时, 如果 k < target，则 ans = 0; 否则, ans = 1;
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[1][i] = 1;
        }
        // 骰子个数
        for (int i = 2; i <= n; i++) {
            // 骰子数目为 n 时, 数字之和
            for (int j = 1; j <= target; j++) {
                // 骰子数目为 n - 1时， 数字之和
                // 最大只能增加k
                for (int x = 1; x < j; x++) {
                    if (x + k < j) {
                        continue;
                    }
                    dp[i][j] = (dp[i][j] + dp[i - 1][x]) % MOD;
                }
            }
        }
        return dp[n][target];
    }
}
