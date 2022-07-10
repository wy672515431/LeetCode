package LeetCode;

import java.util.Arrays;
import java.util.regex.Pattern;

public class 摘樱桃 {
    /**
     * 两个人同时从(0,0)出发，到(N-1,N-1),中间摘到的樱桃最大数
     * 只能向下和向右走
     * x1 + y1 = x2 + y2
     * 他们一共能走2N - 2步
     * dp[k][x1][x2]表示他们走了k步，且第一个人的坐标在x1，第二个的坐标在x2的最大樱桃数
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[2 * n - 1][n][n];
        for (int i = 0; i < 2 * n - 1; i++){
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = grid[0][0];
        for (int k = 1; k <= 2 * n - 2; k++) {
            for (int x1 = Math.max(k - n + 1, 0); x1 <= Math.min(k, n - 1); x1++) {
                //如果是-1,则直接跳过
                if (grid[x1][k - x1] == -1) {
                    continue;
                }
                //默认第二个人在第一个人的下面
                for (int x2 = x1; x2 <= Math.min(k, n - 1); x2++) {
                    if (grid[x2][k - x2] == -1) {
                        continue;
                    }
                    //两个都是向右走
                    int res = dp[k - 1][x1][x2];
                    //向下走，向右走
                    if (x1 > 0){
                        res = Math.max(res, dp[k - 1][x1 - 1][x2]);
                    }
                    if (x2 > 0) {
                        res = Math.max(res, dp[k - 1][x1][x2 - 1]);
                    }
                    if (x1 > 0 && x2 > 0) {
                        res = Math.max(res, dp[k - 1][x1 - 1][x2 - 1]);
                    }
                    res += grid[x1][k - x1];
                    if (x1 != x2) {
                        res += grid[x2][k - x2];
                    }
                    dp[k][x1][x2] = Math.max(dp[k][x1][x2], res);
                }
            }
        }
        //有可能最后无法到达终点，返回0即可。
        return Math.max(dp[2 * n - 2][n - 1][n -1],0);
    }
}
