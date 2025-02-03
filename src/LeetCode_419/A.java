package LeetCode_419;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class A {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (i >= k) {
                map.put(nums[i - k], map.get(nums[i - k]) - 1);
                if (map.get(nums[i - k]) == 0) {
                    map.remove(nums[i - k]);
                }
            }
            if (i >= k - 1) {
                PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
                    if (a.cnt == b.cnt) {
                        return b.val - a.val;
                    }
                    return b.cnt - a.cnt;
                });
                for (int key : map.keySet()) {
                    pq.add(new Node(key, map.get(key)));
                }
                int cnt = x;
                int sum = 0;
                while (!pq.isEmpty() && cnt-- > 0) {
                    Node cur = pq.poll();
                    sum += cur.val * cur.cnt;
                }
                res[i - k + 1] = sum;
            }
        }
        return res;
    }


    static class Node {
        int val;
        int cnt;
        Node(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }
}
