package bytedance.图;

import java.util.ArrayList;
import java.util.Arrays;

public class _3067 {
    public static int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length;
        int[] count = new int[n + 1];
        Graph graph = new Graph(n + 1);
        for (int[] edge : edges) {
            int server1 = edge[0];
            int server2 = edge[1];
            int cost = edge[2];
            graph.addEdge(server1, server2, cost);
            graph.addEdge(server2, server1, cost);
        }
        for (int i = 0; i <= n; i++) {
            count[i] = graph.solve(i, signalSpeed);
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 0, 1}, {2, 1, 1}, {3, 2, 4}, {4, 0, 3}, {5, 4, 1}, {6, 5, 3}};
        countPairsOfConnectableServers(edges, 2);
    }

    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        ArrayList<Edge>[] edges;
        boolean[] isVisited;

        public Graph(int vertices) {
            this.vertices = vertices;
            edges = new ArrayList[this.vertices];
            isVisited = new boolean[this.vertices];
            for (int i = 0; i < this.vertices; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        public void addEdge(int src, int dest, int weight) {
            Edge edge = new Edge(src, dest, weight);
            edges[src].add(edge);
        }

        public int solve(int u, int signalSpeed) {
            ArrayList<Edge> edgeList = edges[u];
            int n = edgeList.size();
            int[] nums = new int[n];
            Arrays.fill(isVisited, false);
            isVisited[u] = true;
            for (int i = 0; i < n; i++) {
                Edge edge = edgeList.get(i);
                int v = edge.dest;
                int weight = edge.weight;
                dfs(v, i, weight, signalSpeed, nums);
            }
            int ans = 0;
            int sum = Arrays.stream(nums).sum();
            for (int i = 0; i < n; i++) {
                ans += (sum - nums[i]) * nums[i];
            }
            return ans / 2;
        }

        /**
         * @param u           当前的节点
         * @param directChild 直接子节点
         * @param weight      当前节点到weight的权重
         */
        private void dfs(int u, int directChild, int weight, int signalSpeed, int[] nums) {
            if (weight % signalSpeed == 0) {
                nums[directChild]++;
            }
            isVisited[u] = true;
            for (Edge edge : edges[u]) {
                int v = edge.dest;
                int w = edge.weight;
                if (!isVisited[v]) {
                    dfs(v, directChild, weight + w, signalSpeed, nums);
                }
            }
        }
    }
}
