package LeetCode_304;

import java.util.Arrays;
import java.util.HashMap;

public class D {
    int[] isVisit;
    int dis = 0;
    int ans = -1;
    HashMap<Integer, Integer> map = new HashMap<>();
    public int longestCycle(int[] edges) {
        isVisit = new int[edges.length];
        Arrays.fill(isVisit, -1);
        for (int i = 0; i < edges.length; i++) {
            dis = 0;
            dfs(edges, i);
        }
        return ans;
    }

    public void dfs(int[] edges, int node) {
        if (node == -1) {
            return;
        }
        if (isVisit[node] ==  1) {
            return;
        }
        map.put(node, dis++);
        isVisit[node]++;
        //第一次访问
        if (map.containsKey(edges[node]) && isVisit[edges[node]] == 0) {
            ans = Math.max(ans, dis - map.get(edges[node]));
            return;
        }
        dfs(edges, edges[node]);
        isVisit[node]++;
    }

    public static void main(String[] args) {
        int[] edges = {-1,4,-1,2,0,4};
        new D().longestCycle(edges);
    }
}
