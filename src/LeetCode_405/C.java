package LeetCode_405;

public class C {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] cntX = new int[n][m];
        int[][] cntY = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = grid[i][j];
                if (i == 0 && j == 0) {
                    cntX[i][j] = ch == 'X' ? 1 : 0;
                    cntY[i][j] = ch == 'Y' ? 1 : 0;
                } else if (i == 0) {
                    cntX[i][j] = cntX[i][j - 1] + (ch == 'X' ? 1 : 0);
                    cntY[i][j] = cntY[i][j - 1] + (ch == 'Y' ? 1 : 0);
                } else if (j == 0) {
                    cntX[i][j] = cntX[i - 1][j] + (ch == 'X' ? 1 : 0);
                    cntY[i][j] = cntY[i - 1][j] + (ch == 'Y' ? 1 : 0);
                } else {
                    cntX[i][j] = cntX[i - 1][j] + cntX[i][j - 1] - cntX[i - 1][j - 1] + (ch == 'X' ? 1 : 0);
                    cntY[i][j] = cntY[i - 1][j] + cntY[i][j - 1] - cntY[i - 1][j - 1] + (ch == 'Y' ? 1 : 0);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cntX[i][j] != 0 && (cntX[i][j] == cntY[i][j])) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
