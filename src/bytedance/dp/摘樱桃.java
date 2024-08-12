package bytedance.dp;

import java.util.Arrays;

public class 摘樱桃 {
    public int cherryPickup(int[][] grid) {
        // 抽象为两个人同时从左上角到右下角的最大收益
        // 两人同时从左上角出发，设在某一时刻其中一个人在(i, j)位置，另一个人在(i', j')位置
        // 满足 i + j = i' + j' = k
        // dp[k][i][i']表示两人从(i, k - i)和(i', k - i')位置到达右下角的最大收益
        // 枚举A和B上一步的走法
        int n = grid.length;
        // 最多走 2 * n - 1 步
        int[][][] dp = new int[2 * n - 1][n][n];
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = grid[0][0];
        for (int k = 1; k < 2 * n - 1; k++) {
            for (int i = Math.max(0, k - n + 1); i < n && i <= k; i++) {
                for (int i1 = Math.max(0, k - n + 1); i1 < n && i1 <= k; i1++) {
                    int j = k - i;
                    int j1 = k - i1;
                    if (grid[i][j] == -1 || grid[i1][j1] == -1) {
                        continue;
                    }
                    int val = grid[i][j];
                    if (i != i1) {
                        val += grid[i1][j1];
                    }
                    // A和B都向右
                    // A向下，B向右
                    // A向右，B向下
                    // A向下，B向下
                    for (int p = -1; p <= 0; p++) {
                        for (int q = -1; q <= 0; q++) {
                            int i0 = i + p;
                            int i10 = i1 + q;
                            if (i0 >= 0 && i0 < n && i10 >= 0 && i10 < n) {
                                dp[k][i][i1] = Math.max(dp[k][i][i1], dp[k - 1][i0][i10] + val);
                            }
                        }
                    }
                }
            }
        }

        return Math.max(0, dp[2 * n - 2][n - 1][n - 1]);
    }
}
