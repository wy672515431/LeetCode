package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.stream.events.EndDocument;

public class 除法求值 {
    List<ArrayList<Edge>> edges = new ArrayList<>();
    // k - var, v - count
    HashMap<String, Integer> map = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int len = queries.size();
        int len1 = equations.size();
        double[] ans = new double[len];
        int count = 0;
        for (int i = 0; i < len1; i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            double value = values[i];
            if (!map.containsKey(var1)) {
                edges.add(new ArrayList<>());
                map.put(var1, count++);
            }
            if (!map.containsKey(var2)) {
                edges.add(new ArrayList<>());
                map.put(var2, count++);
            }
            int i1 = map.get(var1);
            int i2 = map.get(var2);
            edges.get(i1).add(new Edge(i2, value));
            edges.get(i2).add(new Edge(i1, 1.0 / value));
        }
        for (int i = 0; i < len; i++) {
            List<String> query = queries.get(i);
            String u = query.get(0);
            String v = query.get(1);
            ans[i] = solve(u, v);
        }
        return ans;
    }

    class Edge {
        int next;
        double weight;
        public Edge(int next, double weight) {
            this.next = next;
            this.weight = weight;
        }
    }

    public double solve(String u, String v) {
        if (!map.containsKey(u) || !map.containsKey(v)) {
            return -1.0;
        }
        int size = map.size();
        boolean[] isVisited = new boolean[size];
        // path[i]代表i的父节点
        int[] path = new int[size];
        Arrays.fill(path, -1);
        double value = 1.0;
        Queue<Integer> queue = new LinkedList<>();
        int uid = map.get(u);
        int vid = map.get(v);
        queue.offer(uid);
        while (!queue.isEmpty()) {
            int id = queue.poll();
            if (isVisited[id]) {
                continue;
            }
            isVisited[id] = true;
            if (id == vid) {
                // calc
                while (id != -1) {
                    int parent = path[id];
                    if (parent != -1) {
                        int finalId = id;
                        value *= edges.get(parent).stream().filter(o -> o.next == finalId).mapToDouble(o -> o.weight).reduce(1.0, (a, b) -> a * b);
                    }
                    id = parent;
                }
                return value;
            }
            for (Edge edge : edges.get(id)) {
                if (!isVisited[edge.next]) {
                    queue.offer(edge.next);
                    path[edge.next] = id;
                }
            }
        }
        return -1.0;
    }

}
