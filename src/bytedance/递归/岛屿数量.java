package bytedance.递归;

public class 岛屿数量 {
    int n, m, ans = 0;
    boolean[][] isVisit;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numIslands(char[][] grid) {
        n = grid.length + 2;
        m = grid[0].length + 2;
        isVisit = new boolean[n][m];
        // 扩展，将最外层填0
        char[][] extendedGrid = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    extendedGrid[i][j] = '0';
                } else {
                    extendedGrid[i][j] = grid[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (extendedGrid[i][j] == '1' && !isVisit[i][j]) {
                    solve(extendedGrid, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    private void solve(char[][] grid, int i, int j) {
        isVisit[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int x = dirs[k][0];
            int y = dirs[k][1];
            int nextX = i + x;
            int nextY = j + y;
            if (nextX >= 0 && nextX < n && nextY > 0 && nextY < m && !isVisit[nextX][nextY] &&
                grid[nextX][nextY] == '1') {
                solve(grid, nextX, nextY);
            }
        }
    }

}
