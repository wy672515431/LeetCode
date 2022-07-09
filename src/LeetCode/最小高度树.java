package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.management.Query;

public class 最小高度树 {
    //首先我们明确假设树的最长直径为maxHeight，则树的最小高度为 maxHeight / 2; dist[x][y] = maxHeight
    //采用反证法证明。如果存在一个根为root，且树的高度为 h < maxHeight / 2.
    //如果节点root在x~y路径上，则存在 dist[x][root] + dist[root][y] = dist[x][y] < maxHeight，矛盾
    //如果节点root不在x~y路径上，则root~x 和 root~y之间存在一个交点a.由 dist[root][a] + dist[a][x] = h
    // dist[root][a] + dist[a][y] = h    dist[x][y] + 2 * dist[root][a] < maxHeight，矛盾
    //故树的最小高度为 maxHeight / 2
    //最小高度树的根节点一定存在于x~y路径上。
    //假设节点root不存在x~y路径上，且高度为maxHeight / 2
    //推理过程同上
    //所以我们首先需要找到树的最长直径，然后求出中间点。
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        if (n == 2) {
            ans.add(0);
            ans.add(1);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int x = findLongestNode(0, parent, adj);
        int y = findLongestNode(x, parent, adj);
        parent[x] = -1;
        List<Integer> path = new ArrayList<>();
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        if (path.size() % 2 == 0) {
            ans.add(path.get(path.size() / 2 - 1));
        }
        ans.add(path.get(path.size() / 2));
        return ans;
    }


    public int findLongestNode(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisit = new boolean[n];
        queue.offer(u);
        isVisit[u] = true;
        int node = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            node = cur;
            for (int v : adj[cur]) {
                if (!isVisit[v]) {
                    isVisit[v] = true;
                    parent[v] = cur;
                    queue.offer(v);
                }
            }
        }
        return node;
    }
}