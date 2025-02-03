package LeetCode_422;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class B {
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
                        graph.get(nid).add(id);
                    }
                }
            }
        }
        int[] dist = new int[n * m];
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
            for (int v: graph.get(u)) {
                pq.offer(new int[] {v, Math.max(d, moveTime[v / m][v % m]) + 1});
            }
        }
        return dist[n * m - 1];
    }
}
