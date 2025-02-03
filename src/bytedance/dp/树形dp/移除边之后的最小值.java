package bytedance.dp.树形dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 移除边之后的最小值 {
    public long maximizeSumOfWeights(int[][] edges, int k) {
        int n = edges.length + 1;
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        // 节点u和节点v存在一条边，边权为w
        // 选或者不选
        // (not choose, choose)
        return dfs(0, -1, graph, k)[0];
    }

    private long[] dfs(int u, int fa, List<int[]>[] g, int k) {
        long notChoose = 0;
        List<Long> inc = new ArrayList<>();
        for (int[] edge : g[u]) {
            int v = edge[0];
            if (v == fa) {
                continue;
            }
            long[] res = dfs(v, u, g, k);
            // u和v之间的边不选，则v的子节点要选k条边
            notChoose += res[0];
            long diff = res[1] + edge[1] - res[0];
            if (diff > 0) {
                inc.add(diff);
            }
        }
        inc.sort(Collections.reverseOrder());
        for (int i = 0; i < Math.min(inc.size(), k - 1); i++) {
            notChoose += inc.get(i);
        }
        // k - 1条边
        long choose = notChoose;
        if (inc.size() >= k) {
            notChoose += inc.get(k - 1);
        }
        return new long[]{notChoose, choose};
    }
}
