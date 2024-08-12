package bytedance.图;

public class 岛屿数量 {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int n, m, ans;
    boolean[][] vis;
    public int numIslands(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                if (!vis[i][j] && grid[i][j] == '1') {
                    ans++;
                    solve(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void solve(char[][] grid, int x, int y) {
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int x1 = x + dirs[i][0];
            int y1 = y + dirs[i][1];
            if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < m && !vis[x1][y1] && grid[x1][y1] == '1') {
                solve(grid, x1, y1);
            }
        }
    }
}
