package bytedance.数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    // 有点像银行家算法
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Node[] nodes = new Node[n];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(profits[i], capital[i]);
        }
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.capital));
        for (int i = 0; i < n; i++) {
            if (nodes[i].capital <= w) {
                queue.add(nodes[i]);
            } else {
                // 做项目直到大于nodes[i].capital
                while (k > 0 && !queue.isEmpty() && w < nodes[i].capital) {
                    Node node = queue.poll();
                    w += node.profit;
                    k--;
                }
                if (w < nodes[i].capital) {
                    break;
                }
                queue.add(nodes[i]);
            }
        }
        while (k > 0 && !queue.isEmpty()) {
            Node node = queue.poll();
            w += node.profit;
            k--;
        }
        return w;
    }

    static class Node {
        int profit;
        int capital;

        public Node(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }
}
