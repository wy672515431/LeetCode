package bytedance.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 公交路线 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // n条公交线路
        int n = routes.length;
        // 将每条公路作为图的一个节点
        // 当两条公路有交集时，两个节点之间有一条边
        boolean[][] edge = new boolean[n][n];
        // 车站到公交线路的映射
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int busStation : routes[i]) {
                List<Integer> list = map.getOrDefault(busStation, new ArrayList<>());
                for (int conn : list) {
                    edge[i][conn] = true;
                    edge[conn][i] = true;
                }
                list.add(i);
                map.put(busStation, list);
            }
        }
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        // 起点所在的公交线路
        for (int busStation : map.getOrDefault(source, new ArrayList<>())) {
            dis[busStation] = 1;
            queue.add(busStation);
        }
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < n; i++) {
                // 连通
                if (edge[cur][i] && dis[i] == -1) {
                    dis[i] = dis[cur] + 1;
                    queue.offer(i);
                }
            }
        }

        for (int busStation : map.getOrDefault(target, new ArrayList<>())) {
            if (dis[busStation] != -1) {
                ans = Math.min(ans, dis[busStation]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
