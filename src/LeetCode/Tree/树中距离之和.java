package LeetCode.Tree;

import java.util.ArrayList;
import java.util.List;

public class 树中距离之和 {
    int[] ans;
    // sz[u]代表以u为根节点的子树的节点数量
    int[] sz;
    // dp[u]代表以u为根节点的子树，它的所有子节点到u的距离之和。
    int[] dp;
    List<List<Integer>> graph;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ans = new int[n];
        sz = new int[n];
        dp = new int[n];
        graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    /**
     * 将图看作是一个以某一节点为root的树
     * @param u 初始值是树的根节点
     * @param f 节点的父节点
     */
    public void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            dfs(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    /**
     *
     * @param u 初始值是树的根节点
     * @param f 节点的父节点
     *
     */
    public void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }
}
