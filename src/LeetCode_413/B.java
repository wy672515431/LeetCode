package LeetCode_413;

import java.util.Arrays;
import java.util.PriorityQueue;

public class B {
    public int[] resultsArray(int[][] queries, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            int distA = Math.abs(a.x) + Math.abs(a.y);
            int distB = Math.abs(b.x) + Math.abs(b.y);
            return distB - distA;
        });
        int n = queries.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            Node node = new Node(queries[i][0], queries[i][1]);
            if (pq.size() < k) {
                pq.offer(node);
                continue;
            } else {
                Node peek = pq.peek();
                int dist = Math.abs(node.x) + Math.abs(node.y);
                int peekDist = Math.abs(peek.x) + Math.abs(peek.y);
                if (dist < peekDist) {
                    pq.poll();
                    pq.offer(node);
                }
            }
            res[i] = Math.abs(pq.peek().x) + Math.abs(pq.peek().y);
        }
        return res;
    }

    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
