package bytedance.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 并行课程 {
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> edges = new ArrayList<>();
        int[] inDegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] relation : relations) {
            edges.get(relation[0]).add(relation[1]);
            inDegree[relation[1]]++;
        }
        Queue<Node> queue = new ArrayDeque<>();
        int count = 0;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(new Node(i, 1));
                count++;
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            res = Math.max(res, node.semester);
            for (int next : edges.get(node.node)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(new Node(next, node.semester + 1));
                    count++;
                }
            }
        }
        if (count != n) {
            return -1;
        }
        return res;
    }

    static class Node {
        int node;
        int semester;

        Node(int node, int semester) {
            this.node = node;
            this.semester = semester;
        }
    }
}
