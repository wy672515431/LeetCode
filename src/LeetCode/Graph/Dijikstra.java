package LeetCode.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijikstra {
    /**
     * 复杂度O(mlogm) 堆优化适用于稀疏图
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 邻接表建图
        List<ArrayList<MyEdge>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            MyEdge myEdge = new MyEdge(v, w);
            edges.get(u - 1).add(myEdge);
        }
        // 源节点，求源节点到其他节点的最短距离
        int source = k;
        // 距离数组
        int[] dist = new int[n];
        boolean[] isVisited = new boolean[n];
        // init
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source - 1] = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.dis));
        priorityQueue.offer(new Node(source, dist[source - 1]));
        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().node;
            if (isVisited[u - 1]) {
                continue;
            }
            isVisited[u - 1] = true;
            for (MyEdge myEdge : edges.get(u - 1)) {
                int v = myEdge.desNode;
                int w = myEdge.weight;
                if (dist[v - 1] > dist[u - 1] + w) {
                    dist[v - 1] = dist[u - 1] + w;
                    priorityQueue.offer(new Node(v, dist[v - 1]));
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int dis : dist) {
            ans = Math.max(ans, dis);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 1.将顶点分为两个集合，S和U
     * S代表已经求出最短路径的节点。U相反
     * 初始时 S = {source}
     * 2.dist[source] = 0
     * 3.dist[v] = w[source][v]
     * 4.找到U中相对于集合S的最短路径中距离最短的顶点v
     * 5.将该顶点加入S集合
     * 6.将顶点v加入后，会对U中节点相对于S的最短路径产生影响，更新节点。
     * o(n^2)稠密图
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int x = -1;
            // 找到U集合中的相对于S的最短路径。
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
}

class MyEdge {

    // 有向边的尾
    int desNode;
    int weight;

    public MyEdge(int desNode, int weight) {
        this.desNode = desNode;
        this.weight = weight;
    }
}

class Node {
    int node;
    // 源节点到node的距离
    int dis;

    public Node(int node, int dis) {
        this.node = node;
        this.dis = dis;
    }
}