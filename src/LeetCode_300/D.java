package LeetCode_300;

import java.util.Arrays;

public class D {
    static final int mod = 1000000007;
    int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int[][] memo;
    int m, n;
    public int countPaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = (ans + dfs(i, j, grid)) % mod;
            }
        }
        return ans;
    }
    private int dfs(int x, int y, int[][] grid) {
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        int num = 1;
        for (int k = 0; k < directions.length; k++) {
            int nextX = x + directions[k][1];
            int nextY = y + directions[k][0];
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] > grid[x][y] ) {
                num = (num + dfs(nextX, nextY, grid)) % mod;
            }
        }
        memo[x][y] = num;
        return num;
    }
}
