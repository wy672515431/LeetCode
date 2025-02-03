package LeetCode_426;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C {
    public static void main(String[] args) {
        C c = new C();
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
        int[] ans = c.maxTargetNodes(edges1, edges2, 2);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
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
        int[][] ndp = new int[n][k + 1];
        int[][] mdp = new int[m][k + 1];
        dfs1(0, -1, graph1, ndp);
        dfs1(0, -1, graph2, mdp);
        dfs2(0, -1, graph1, ndp);
        dfs2(0, -1, graph2, mdp);
        int[] sum = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= k - 1; j++) {
                sum[i] += mdp[i][j];
            }
        }
        int max = Arrays.stream(sum).max().getAsInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                ans[i] += ndp[i][j];
            }
            ans[i] += max;
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
            for (int i = 1; i < dp[u].length; i++) {
                dp[u][i] += dp[v][i - 1];
            }
        }
    }

    private void dfs2(int u, int fa, List<ArrayList<Integer>> graph, int[][] dp) {
        if (fa != -1) {
            int[] ndp = Arrays.copyOf(dp[u], dp[u].length);
            for (int i = 0; i < dp[u].length - 1; i++) {
                dp[u][i + 1] += dp[fa][i];
                if (i > 0) {
                    dp[u][i + 1] -= ndp[i - 1];
                }
            }
        }
        for (int v : graph.get(u)) {
            if (v == fa) {
                continue;
            }
            dfs2(v, u, graph, dp);
        }
    }
}
