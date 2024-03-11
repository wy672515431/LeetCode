package LeetCode_92_Double;

public class B {
    public int[][] onesMinusZeros(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] diff = new int[n][m];
        int[][] row = new int[n][2];
        int[][] col = new int[m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                row[i][grid[i][j]]++;
                col[j][grid[i][j]]++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                diff[i][j] = row[i][1] + col[j][1] - row[i][0] - col[j][0];
            }
        }
        return diff;
    }
}
