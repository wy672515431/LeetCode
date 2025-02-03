package CF.CF_974_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class E {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            String[] input = bf.readLine().split(" ");
            // n vertices, m edges, h horses
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int h = Integer.parseInt(input[2]);
            Set<Integer> horses = Arrays.stream(bf.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            // 两人相遇，求两次最短路径
            List<ArrayList<int[]>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                String[] edge = bf.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                int w = Integer.parseInt(edge[2]);
                graph.get(u - 1).add(new int[]{v - 1, w});
                graph.get(v - 1).add(new int[]{u - 1, w});
            }
            long[] dist1 = dijkstra(graph, 0, horses);
            long[] dist2 = dijkstra(graph, 2 * n - 2, horses);
            long ans = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                ans = Math.min(ans, Math.max(dist1[i], dist2[i]));
            }
            System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
        }
        bf.close();
    }

    private static long[] dijkstra(List<ArrayList<int[]>> graph, int s, Set<Integer> horses) {
        int n = graph.size();
        // 对于节点i, 2 * i表示当前没有骑马，2 * i + 1表示当前骑马
        long[] dist = new long[2 * n];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{s, 0L});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long u = cur[0];
            long dis = cur[1];
            if (dist[(int) u] != Long.MAX_VALUE) {
                continue;
            }
            dist[(int) u] = dis;
            int realU = (int) u / 2;
            boolean onHorse = (int) u % 2 != 0;
            if (!onHorse && horses.contains(realU + 1)) {
                // 骑马
                pq.offer(new long[]{2 * realU + 1, dis});
            }
            for (int[] edge : graph.get(realU)) {
                int v = edge[0];
                int w = edge[1];
                if (onHorse) {
                    // 骑马
                    pq.offer(new long[]{2L * v + 1, dis + w / 2});
                } else {
                    // 不骑马
                    pq.offer(new long[]{2L * v, dis + w});
                }
            }
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Math.min(dist[2 * i], dist[2 * i + 1]);
        }
        return ans;
    }
}
