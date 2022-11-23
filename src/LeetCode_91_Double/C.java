package LeetCode_91_Double;

import java.util.ArrayList;
import java.util.Arrays;

public class C {
    private ArrayList<Integer>[] graph;
    private boolean ok = false;
    private int ans = Integer.MIN_VALUE;
    private int sum = 0;
    private boolean[] isVisited;
    private boolean[] isVisited1;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = edges.length + 1;
        isVisited = new boolean[n];
        isVisited1 = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        dfs(bob, 0, list);
        Arrays.fill(isVisited1, false);
        dfs1(0, list, amount, 0);
        return ans;
    }

    public void dfs(int cur, int end, ArrayList<Integer> list) {
        list.add(cur);
        isVisited1[cur] = true;
        if (cur == end) {
            ok = true;
            return;
        }
        for (int i = 0; i < graph[cur].size(); i++) {
            if (!ok && !isVisited1[graph[cur].get(i)]) {
                dfs(graph[cur].get(i), end, list);
            }
        }
        if (!ok) {
            list.remove((Integer) cur);
            isVisited[cur] = false;
        }
    }

    public void dfs1(int cur, ArrayList<Integer> list, int[] amount, int cnt) {
        if (isVisited1[cur]) {
            return;
        }
        // 处理alice
        isVisited1[cur] = true;
        // Bob没有访问过
        if (!isVisited[cur]) {
            if (cnt < list.size() && cur == list.get(cnt)) {
                sum += amount[cur] / 2;
            } else {
                sum += amount[cur];
            }
        }
        // 处理bob
        if (cnt < list.size()) {
            isVisited[list.get(cnt)] = true;
        }
        // 判断是不是叶子节点
        if (graph[cur].size() == 1 && cur != 0) {
            ans = Math.max(ans, sum);
        }
        for (int i = 0; i < graph[cur].size(); i++) {
            dfs1(graph[cur].get(i), list, amount, cnt + 1);
        }

        // 处理alice
        isVisited1[cur] = false;
        // 处理bob
        if (cnt < list.size()) {
            isVisited[list.get(cnt)] = false;
        }
        // Bob没有访问过
        if (!isVisited[cur]) {
            if (cnt < list.size() && cur == list.get(cnt)) {
                sum -= amount[cur] / 2;
            } else {
                sum -= amount[cur];
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        int[] amount = {-2,4,2,-4,6};
        C c = new C();
        c.mostProfitablePath(edges, 3, amount);
    }

}
