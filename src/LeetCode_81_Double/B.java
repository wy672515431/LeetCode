package LeetCode_81_Double;

import java.util.ArrayList;
import java.util.Arrays;

public class B {
    public long countPairs(int n, int[][] edges) {
        ArrayList<Integer>[] map = new ArrayList[n];
        Arrays.setAll(map, vertex -> new ArrayList<>());
        long ans = 0;
        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            map[start].add(end);
            map[end].add(start);
        }
        boolean[] isVisit = new boolean[n];
        ArrayList<Integer> countOfConnectedComponents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isVisit[i]) {
                ArrayList<Integer> tem = new ArrayList<>();
                dfs(i, isVisit, map, tem);
                countOfConnectedComponents.add(tem.size());
            }
        }
        int rest = 0;
        for (int i = 0; i < countOfConnectedComponents.size(); i++) {
            ans += (1L * countOfConnectedComponents.get(i) * (n - countOfConnectedComponents.get(i) - rest));
            rest = rest + countOfConnectedComponents.get(i); 
        }
        return ans;
    }

    private void dfs(int vertex, boolean[] isVisit, ArrayList<Integer>[] map, ArrayList<Integer> tem) {
        if (isVisit[vertex]) {
            return;
        }
        isVisit[vertex] = true;
        tem.add(vertex);
        for (int i = 0; i < map[vertex].size(); i++) {
            dfs(map[vertex].get(i), isVisit, map, tem);
        }
    }
}
