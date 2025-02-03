package LeetCode_422;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class C {
    private static final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minTimeToReach(int[][] moveTime) {
        List<List<Integer>> graph = new ArrayList<>();
        int n = moveTime.length;
        int m = moveTime[0].length;
        for (int i = 0; i < n * m; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int id = i * m + j;
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                        int nid = ni * m + nj;
                        graph.get(id).add(nid);
                    }
                }
            }
        }
        // 2 * i代表1s，2 * i + 1代表2s
        int[] dist = new int[2 * n * m];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];
            if (dist[u] != Integer.MAX_VALUE) {
                continue;
            }
            dist[u] = d;
            for (int v: graph.get(u / 2)) {
                int flag = u % 2;
                if (flag == 0) {
                    pq.offer(new int[] {2 * v + 1, Math.max(d, moveTime[v / m][v % m]) + 1});
                } else {
                    pq.offer(new int[] {2 * v, Math.max(d, moveTime[v / m][v % m]) + 2});
                }
            }
        }
        return Math.min(dist[2 * n * m - 2], dist[2 * n * m - 1]);
    }
}
