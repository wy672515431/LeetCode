package LeetCode_303;

public class B {
    public int equalPairs(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean ok = true;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] != grid[k][j]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
