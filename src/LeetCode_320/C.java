package LeetCode_320;

import java.util.ArrayList;

public class C {
    private long ans = 0L;
    private boolean isVisited[];
    private int[] scores;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        ArrayList<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        isVisited = new boolean[n];
        scores = new int[n];
        for (int[] road : roads) {
            edges[road[0]].add(road[1]);
            edges[road[1]].add(road[0]);
        }
        dfs(edges, 0);
        for (int i = 1; i < n; i++) {
            ans += (scores[i] % seats == 0) ? (scores[i] / seats) : (scores[i] / seats + 1);
        }
        return ans;
    }

    public int dfs(ArrayList<Integer>[] edges, int root) {
        isVisited[root] = true;
        int score = 1;
        if (edges[root].size() == 1 && root != 0) {
            scores[root] = score;
            isVisited[root] = false;
            return score;
        }
        for (int next : edges[root]) {
            if (!isVisited[next]) {
                score += dfs(edges, next);
            }
        }
        scores[root] = score;
        return score;
    }

    public static void main(String[] args) {
        C c = new C();
        int[][] roads = {{0, 1}, {1, 2}};
        c.minimumFuelCost(roads, 3);
    }
}
