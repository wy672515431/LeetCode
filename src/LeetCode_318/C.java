package LeetCode_318;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class C {
    public long totalCost(int[] costs, int k, int candidates) {
        long totalCost = 0L;
        TreeSet<Node> set = new TreeSet<>(Comparator.comparingInt((Node o) -> o.cost).thenComparingInt(o -> o.index));
        // 2 * candidates >= length
        int length = costs.length;
        int preIndex = candidates - 1;
        int postIndex = length - candidates;
        // init
        for (int i = 0; i <= Math.min(preIndex, length - 1); i++) {
            set.add(new Node(i, costs[i]));
        }
        for (int i = Math.max(preIndex + 1, postIndex); i < length; i++) {
            set.add(new Node(i, costs[i]));
        }
        for (int i = 0; i < k; i++) {
            Node targetWorker = set.pollFirst();
            totalCost += targetWorker.cost;
            if (preIndex < postIndex) {
                if (targetWorker.index <= preIndex) {
                    set.add(new Node(preIndex + 1, costs[preIndex + 1]));
                    preIndex++;
                } else if (targetWorker.index >= postIndex) {
                    set.add(new Node(postIndex - 1, costs[postIndex - 1]));
                    postIndex--;
                }
            }
        }
        return totalCost;
    }

    class Node {
        int index;
        int cost;
        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
