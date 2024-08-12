package LeetCode_136_Double;

public class B {
    public int minFlips(int[][] grid) {
        int times1 = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            int s = 0, e = m - 1;
            while (s < e) {
                if (grid[i][s] != grid[i][e]) {
                    times1++;
                }
                s++;
                e--;
            }
        }
        int times2 = 0;
        for (int i = 0; i < m; i++) {
            int s = 0, e = n - 1;
            while (s < e) {
                if (grid[s][i] != grid[e][i]) {
                    times2++;
                }
                s++;
                e--;
            }
        }
        return Math.min(times1, times2);
    }
}
