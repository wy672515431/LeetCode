package LeetCode.Graph;

import java.util.ArrayList;
import java.util.List;

public class 最大网络秩 {
    List<ArrayList<Integer>> edges = new ArrayList<>();
    public int maximalNetworkRank(int n, int[][] roads) {
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(i, j)) {
                    ans = Math.max(ans, edges.get(i).size() + edges.get(j).size() - 1);
                } else {
                    ans = Math.max(ans, edges.get(i).size() + edges.get(j).size());
                }
            }
        }
        return ans;
    }

    public boolean check(int u, int v) {
        return edges.get(u).stream().anyMatch(o -> o == v);
    }
}
