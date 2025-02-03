package bytedance.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 最佳的碰头地点 {
    /**
     * 选择一个点，使得所有点到这个点的距离之和最小
     * 思路：找到中位数
     * @param grid
     * @return
     */
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(rows);
        Collections.sort(cols);
        int midRow = rows.get(rows.size() / 2);
        int midCol = cols.get(cols.size() / 2);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    ans += Math.abs(i - midRow) + Math.abs(j - midCol);
                }
            }
        }
        return ans;
    }
}
