package LeetCode_304;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class C {
    boolean[] isVisit;
    int dis = 0;
    // k - node v - dis
    HashMap<Integer, Integer> map1 = new HashMap<>();
    HashMap<Integer, Integer> map2 = new HashMap<>();
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        isVisit = new boolean[edges.length];
        dfs1(edges, node1);
        Arrays.fill(isVisit, false);
        dis = 0;
        dfs2(edges, node2);
        int ans = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            int node = entry.getKey();
            if (map2.containsKey(node)) {
                if (max > Math.max(entry.getValue(), map2.get(node))) {
                    max = Math.max(entry.getValue(), map2.get(node));
                    ans = node;
                } else if (max == Math.max(entry.getValue(), map2.get(node))) {
                    ans = Math.min(ans, node);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public void dfs1(int[] edges, int node1) {
        if (node1 == -1) {
            return;
        }
        if (isVisit[node1]) {
            return;
        }
        isVisit[node1] = true;
        map1.put(node1, dis++);
        dfs1(edges, edges[node1]);
    }

    public void dfs2(int[] edges, int node2) {
        if (node2 == -1) {
            return;
        }
        if (isVisit[node2]) {
            return;
        }
        isVisit[node2] = true;
        map2.put(node2, dis++);
        dfs2(edges, edges[node2]);
    }
}
