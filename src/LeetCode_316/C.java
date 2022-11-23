package LeetCode_316;

import java.util.Arrays;
import java.util.Comparator;

public class C {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            Node node = new Node(nums[i], cost[i]);
            nodes[i] = node;
        }
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.num));
        long total_sum = 0L;
        for (int i = 0; i < n; i++) {
            total_sum += nodes[i].cost;
        }
        long ans = 0L;
        long sum = 0L;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            sum += nodes[i].cost;
            if (sum >= total_sum - sum) {
                pos = i;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            ans = (ans + ((long) nodes[i].cost * Math.abs(nodes[i].num - nodes[pos].num)));
        }
        return ans;
    }
}

class Node {
    int num;
    int cost;

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}
