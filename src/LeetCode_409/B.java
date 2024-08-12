package LeetCode_409;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class B {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        List<ArrayList<Edge>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int v = i + 1;
            int w = 1;
            Edge edge = new Edge(v, w);
            edges.get(i).add(edge);
        }
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int u = query[0];
            int v = query[1];
            int w = 1;
            Edge edge = new Edge(v, w);
            edges.get(u).add(edge);
            int source = 0;
            int[] dist = new int[n];
            boolean[] isVisited = new boolean[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.dis));
            priorityQueue.offer(new Node(source, dist[source]));
            while (!priorityQueue.isEmpty()) {
                int cur = priorityQueue.poll().node;
                if (isVisited[cur]) {
                    continue;
                }
                isVisited[cur] = true;
                for (Edge e : edges.get(cur)) {
                    int next = e.desNode;
                    int weight = e.weight;
                    if (dist[next] > dist[cur] + weight) {
                        dist[next] = dist[cur] + weight;
                        priorityQueue.offer(new Node(next, dist[next]));
                    }
                }
            }
            ans[i] = dist[n - 1];
        }
        return ans;
    }

    static class Edge {
        // 有向边的尾
        int desNode;
        int weight;

        public Edge(int desNode, int weight) {
            this.desNode = desNode;
            this.weight = weight;
        }
    }

    static class Node {
        int node;
        // 源节点到node的距离
        int dis;

        public Node(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }
    }
}
