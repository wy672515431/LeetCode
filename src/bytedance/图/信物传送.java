package bytedance.图;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 信物传送 {
    char[] dirs = {'>', '<', '^', 'v'};
    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        int n = matrix.length;
        int m = matrix[0].length();
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < m * n; i++) {
            graph.add(new ArrayList<>());
        }
        // build graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int idx = i * m + j;
                char ch = matrix[i].charAt(j);
                int nIdx = calc(idx, ch, m, n);
                if (nIdx != -1) {
                    graph.get(idx).add(new int[]{nIdx, 0});
                }
                for (int k = 0; k < 4; k++) {
                    if (dirs[k] != ch) {
                        nIdx = calc(idx, dirs[k], m, n);
                        if (nIdx != -1) {
                            graph.get(idx).add(new int[]{nIdx, 1});
                        }
                    }
                }
            }
        }
        int source = start[0] * m + start[1];
        int target = end[0] * m + end[1];
        int[] dist = new int[n * m];
        Arrays.fill(dist, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[] {source, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int idx = cur[0];
            int dis = cur[1];
            if (dist[idx] != -1) {
                continue;
            }
            dist[idx] = dis;
            for (int[] edge : graph.get(idx)) {
                int v = edge[0];
                int w = edge[1];
                pq.offer(new int[]{v, dis + w});
            }
        }
        return dist[target];
    }

    private int calc(int idx, char ch, int m, int n) {
        int x = idx / m;
        int y = idx % m;
        int nx = 0, ny = 0;
        if (ch == '>') {
            nx = x;
            ny = y + 1;
        } else if (ch == '<') {
            nx = x;
            ny = y - 1;
        } else if (ch == '^') {
            nx = x - 1;
            ny = y;
        } else if (ch == 'v') {
            nx = x + 1;
            ny = y;
        }
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return -1;
        }
        return nx * m + ny;
    }
}
