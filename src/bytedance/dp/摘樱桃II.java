package bytedance.dp;

import java.util.Arrays;

public class 摘樱桃II {
    public int cherryPickup(int[][] grid) {
        // 机器人1在(0, 0)，机器人2在(0, col - 1)，他们能向左下、下、右下走
        int n = grid.length;
        int m = grid[0].length;
        // 第一维表示机器人1和2所在的行数，他们所在的行数一定是相同的，第二维和第三维分别代表
        int[][][] dp = new int[n][m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[0][0][m - 1] = grid[0][0] + grid[0][m - 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    for (int x = -1; x <= 1; x++) {
                        int j1 = j + x;
                        if (j1 < 0 || j1 >= m) {
                            continue;
                        }
                        for (int y = -1; y <= 1; y++) {
                            int k1 = k + y;
                            if (k1 < 0 || k1 >= m) {
                                continue;
                            }
                            int val = grid[i][j];
                            if (j != k) {
                                val += grid[i][k];
                            }
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j1][k1] + val);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dp[n - 1][i][j]);
            }
        }
        return ans;
    }
}
