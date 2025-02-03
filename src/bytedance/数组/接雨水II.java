package bytedance.数组;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 接雨水II {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] isVisited = new boolean[n][m];
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // border
                // treat like the wall
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    pq.offer(new Cell(i, j, heightMap[i][j]));
                    isVisited[i][j] = true;
                }
            }
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            for (int[] dir : dirs) {
                int x = cur.x + dir[0];
                int y = cur.y + dir[1];
                if (x < 0 || x >= n || y < 0 || y >= m || isVisited[x][y]) {
                    continue;
                }
                isVisited[x][y] = true;
                if (heightMap[x][y] < cur.height) {
                    ans += cur.height - heightMap[x][y];
                }
                pq.offer(new Cell(x, y, Math.max(cur.height, heightMap[x][y])));
            }
        }
        return ans;
    }

    static class Cell {
        int x;
        int y;
        int height;

        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}

