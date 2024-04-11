package bytedance.递归;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class 腐烂的橘子 {
    public static void main(String[] args) {
        int cap = 4;
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        System.out.println(n);
    }
    /**
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂
     * 1 代表新鲜橘子
     * 2 代表腐烂的橘子
     * 我们可以将所有腐烂橘子放入到队列中
     *
     * @param grid
     * @return
     */
    int ans = 0;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int m, n;

    public int orangesRotting(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        // key - 节点, v - 被腐烂的最短时间
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(i * n + j);
                    map.put(i * n + j, 0);
                }
            }
        }
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int x = pos / n;
            int y = pos % n;
            int depth = map.get(pos);
            for (int i = 0; i < 4; i++) {
                int x1 = x + dirs[i][0];
                int y1 = y + dirs[i][1];
                if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && grid[x1][y1] == 1) {
                    // 被腐烂
                    grid[x1][y1] = 2;
                    queue.offer(x1 * n + y1);
                    int time = depth + 1;
                    ans = Math.max(time, ans);
                    map.put(x1 * n + y1, time);
                }
            }
        }
        for (int[] row : grid) {
            for (int num : row) {
                // 存在还没被腐烂的橘子
                if (num == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }
}
