package LeetCode_418;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class C {
    public int[][] constructGridLayout(int n, int[][] edges) {
        List<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 一个点只有一条边：则矩阵的n = 1
        // 一个点有两条边：矩阵n = 1时，其不能是顶点，矩阵n > 1时，其必须是顶点
        // 一个点有三条边：必须 n > 1，且其在第一行、第一列、最后一行、最后一列，不在顶点的位置
        // 一个点有四条边
        int minSize = Integer.MAX_VALUE;
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            minSize = Math.min(minSize, graph.get(i).size());
            maxSize = Math.max(maxSize, graph.get(i).size());
        }
        if (minSize == 1) {
            assert (maxSize == 2);
            int[][] res = new int[1][n];
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (graph.get(i).size() == 1) {
                    queue.offer(i);
                    break;
                }
            }
            int index = 0;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (visited[cur]) {
                    continue;
                }
                visited[cur] = true;
                res[0][index++] = cur;
                for (int next : graph.get(cur)) {
                    queue.offer(next);
                }
            }
            return res;
        } else {
            assert (minSize == 2);
            if (maxSize == 2) {
                assert (n == 4);
                int[][] res = new int[2][2];
                res[0][0] = 0;
                res[0][1] = 1;
                res[1][0] = 2;
                res[1][1] = 3;
                return res;
            } else if (maxSize == 3) {
                // 一个点有三条边：必须 n > 1，且其在第一行、第一列、最后一行、最后一列，不在顶点的位置
                int[][] res = new int[2][n / 2];
                boolean[] visited = new boolean[n];
                int[] point = {0, n / 2 - 1, n / 2, n - 1};
                Map<Integer, Integer> map = new HashMap<>();
                int cnt = 0;
                for (int i = 0; i < n; i++) {
                    if (graph.get(i).size() == 2) {
                        map.put(i, point[cnt++]);
                    }
                }
                assert (cnt == 2);

            }
            return null;
        }
    }
}
