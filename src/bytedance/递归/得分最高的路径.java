package bytedance.递归;

import 数据结构学习计划.扁平化多级双向链表;

import java.util.ArrayDeque;
import java.util.Queue;

public class 得分最高的路径 {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;
    public int maximumMinimumPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int low = 0;
        // 必须经过(0, 0) 和 (m - 1, n - 1)
        int high = Math.min(grid[0][0], grid[m - 1][n - 1]);
        int ans = -1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            // 不存在，说明值太大了
            if (!pathExists(grid, mid)) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

    public boolean pathExists(int[][] grid, int val) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = pos[0] + dirs[i][0];
                int y = pos[1] + dirs[i][1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] < val) {
                    continue;
                }
                if (x == m - 1 && y == n - 1) {
                    return true;
                }

                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }

        return false;
    }
}
