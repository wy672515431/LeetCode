package bytedance.图;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 水资源分配优化 {
    /**
     * 最小生成树
     * Kruskal and Prime
     * @param n
     * @param wells
     * @param pipes
     * @return
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        Graph graph = new Graph(n + 1);
        for (int i = 0; i < n; i++) {
            graph.addEdge(0, i + 1, wells[i]);
            graph.addEdge(i + 1, 0, wells[i]);
        }
        for (int[] pipe : pipes) {
            int house1 = pipe[0];
            int house2 = pipe[1];
            int cost = pipe[2];
            graph.addEdge(house1, house2, cost);
            graph.addEdge(house2, house1, cost);
        }
        return graph.primMST();
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
        // 点数量
        int vertices;
        ArrayList<Edge>[] edges;

        public Graph(int vertices) {
            this.vertices = vertices;
            edges = new ArrayList[this.vertices];
            for (int i = 0; i < this.vertices; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        public void addEdge(int src, int dest, int weight) {
            Edge edge = new Edge(src, dest, weight);
            edges[src].add(edge);
        }

        public int primMST() {
            boolean[] inHeap = new boolean[vertices];
            int[] minCost = new int[vertices];
            Arrays.fill(minCost, Integer.MAX_VALUE);
            PriorityQueue<Edge> queue = new PriorityQueue<>(
                    Comparator.comparingInt(o -> o.weight)
            );
            queue.offer(new Edge(0, 0, minCost[0]));
            while (!queue.isEmpty()) {
                int u = queue.poll().dest;
                inHeap[u] = true;
                for (int i = 0; i < edges[u].size(); i++) {
                    Edge edge = edges[u].get(i);
                    int v = edge.dest;
                    int weight = edge.weight;
                    if (!inHeap[v] && minCost[v] > weight) {
                        minCost[v] = weight;
                        queue.offer(new Edge(u, v, minCost[v]));
                    }
                }
            }
            int ans = 0;
            for (int i = 1; i < vertices; i++) {
                ans += minCost[i];
            }
            return ans;
        }
    }
}
