package LeetCode_426;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        List<ArrayList<Integer>> graph1 = new ArrayList<>();
        List<ArrayList<Integer>> graph2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph1.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            graph2.add(new ArrayList<>());
        }
        for (int[] edge : edges1) {
            int u = edge[0], v = edge[1];
            graph1.get(u).add(v);
            graph1.get(v).add(u);
        }
        for (int[] edge : edges2) {
            int u = edge[0], v = edge[1];
            graph2.get(u).add(v);
            graph2.get(v).add(u);
        }
        int[] ans = new int[n];
        int[][] ndp = new int[n][2];
        int[][] mdp = new int[m][2];
        dfs1(0, -1, graph1, ndp);
        dfs1(0, -1, graph2, mdp);
        dfs2(0, -1, graph1, ndp);
        dfs2(0, -1, graph2, mdp);
        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, mdp[i][1]);
        }
        for (int i = 0; i < n; i++) {
            ans[i] = ndp[i][0] + max;
        }
        return ans;
    }

    private void dfs1(int u, int fa, List<ArrayList<Integer>> graph, int[][] dp) {
        // 一个节点是它自身的目标节点，且路径长度为0
        dp[u][0] = 1;
        for (int v : graph.get(u)) {
            if (v == fa) {
                continue;
            }
            dfs1(v, u, graph, dp);
            dp[u][1] += dp[v][0];
            dp[u][0] += dp[v][1];
        }
    }

    private void dfs2(int u, int fa, List<ArrayList<Integer>> graph, int[][] dp) {
        if (fa != -1) {
            int[] ndp = Arrays.copyOf(dp[u], dp[u].length);
            dp[u][0] += (dp[fa][1] - ndp[0]);
            dp[u][1] += (dp[fa][0] - ndp[1]);
        }
        for (int v : graph.get(u)) {
            if (v == fa) {
                continue;
            }
            dfs2(v, u, graph, dp);
        }
    }
}
