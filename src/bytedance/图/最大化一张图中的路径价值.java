package bytedance.图;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class 最大化一张图中的路径价值 {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4};
        int[][] edges = {{0, 1, 10}, {1, 2, 11}, {2, 3, 12}, {1, 3, 13}};
        int maxTime = 50;
        System.out.println(new 最大化一张图中的路径价值().maximalPathQuality(values, edges, maxTime));
    }
    private int ans = 0;
    private final Map<Integer, Integer> visited = new HashMap<>();
    private final Set<EdgePair> visitedEdge = new HashSet<>();
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
            graph[edge[1]].add(new Edge(edge[0], edge[2]));
        }
        solve(values, maxTime, graph, 0, 0);
        return ans;
    }

    private void solve(int[] values, int maxTime, ArrayList<Edge>[] graph, int curNode, int curTime) {
        if (curTime > maxTime) {
            return;
        }
        visited.put(curNode, visited.getOrDefault(curNode, 0) + 1);
        if (curNode == 0) {
            int value = visited.entrySet().stream().filter(entry -> entry.getValue() > 0).reduce(0, (acc, entry) -> acc + values[entry.getKey()], Integer::sum);
            ans = Math.max(ans, value);
        }
        for (Edge edge : graph[curNode]) {
            EdgePair edgePair = new EdgePair(curNode, edge.dest);
            if (visitedEdge.contains(edgePair)) {
                continue;
            }
            visitedEdge.add(edgePair);
            visited.put(edge.dest, visited.getOrDefault(edge.dest, 0) + 1);
            solve(values, maxTime, graph, edge.dest, curTime + edge.weight);
            visited.put(edge.dest, visited.get(edge.dest) - 1);
            visitedEdge.remove(edgePair);
        }
        visited.put(curNode, visited.get(curNode) - 1);
    }

    static class Edge {
        int dest;
        int weight;
        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class EdgePair {
        int from;
        int dest;
        public EdgePair(int from, int dest) {
            this.from = from;
            this.dest = dest;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EdgePair edgePair = (EdgePair) o;
            return from == edgePair.from && dest == edgePair.dest;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, dest);
        }
    }
}
