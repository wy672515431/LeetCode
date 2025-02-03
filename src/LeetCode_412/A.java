package LeetCode_412;

import java.util.PriorityQueue;

public class A {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 5, 6};
        int k = 30;
        int multiplier = 2;
        A a = new A();
        int[] ans = a.getFinalState(nums, k, multiplier);
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.val == b.val) {
                return a.index - b.index;
            }
            return a.val - b.val;
        });
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, nums[i]);
            pq.offer(nodes[i]);
        }
        while (k-- > 0) {
            Node node = pq.poll();
            node.val *= multiplier;
            pq.offer(node);
        }
        int[] ans = new int[n];
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            ans[node.index] = node.val;
        }
        return ans;
    }

    static class Node {
        int index;
        int val;

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}
