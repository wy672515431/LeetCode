package LeetCode_412;

import java.math.BigInteger;
import java.util.PriorityQueue;

public class C {
    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 2};
        int k = 4;
        int multiplier = 2;
        C c = new C();
        int[] ans = c.getFinalState(nums, k, multiplier);
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    private static final int MOD = (int) 1e9 + 7;

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // 2 1 3 5 6
        // 我们把每个数字都变成 multiplier ^ x + y 的形式, x的最大不会很大

        if (multiplier == 1) {
            return nums;
        }
        int n = nums.length;
        int[] ans = new int[n];
        if (n == 1) {
            ans[0] = (int) ((long) nums[0] * fastPow(multiplier, k) % MOD);
            return ans;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.x == b.x) {
                if (a.y.compareTo(b.y) == 0) {
                    return a.index - b.index;
                }
                return a.y.compareTo(b.y);
            }
            return a.x - b.x;
        });
        Node[] nodes = new Node[n];
        // 找到最大的x
        int maxX = 0;
        for (int i = 0; i < n; i++) {
            // x = log_{multiplier} (nums[i])
//            int x = (int) (Math.log(nums[i]) / Math.log(multiplier));
            int x = 0;
            if (nums[i] == 1) {
                x = 0;
            } else {
                long mult = multiplier;
                while (mult <= nums[i]) {
                    mult *= multiplier;
                    x++;
                }
            }
            int temp = nums[i] - fastPow(multiplier, x);
            BigInteger y = BigInteger.valueOf(temp);
            maxX = Math.max(maxX, x);
            nodes[i] = new Node(i, x, y);
            pq.offer(nodes[i]);
        }
        int all = 0;
        boolean flag = false;
        while (k > 0) {
            Node node = pq.poll();
            if (node.x == maxX && !flag) {
                all += k / n;
                k %= n;
                flag = true;
            }
            if (k == 0) {
                pq.offer(node);
                break;
            }
            node.x++;
            node.y = node.y.multiply(BigInteger.valueOf(multiplier));
            node.times++;
            pq.offer(node);
            k--;
        }
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            ans[node.index] = (int) ((long) nums[node.index] * fastPow(multiplier, node.times + all) % MOD);
        }
        return ans;
    }

    // a ^ b % MOD
    private int fastPow(int a, int b) {
        int res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (int) ((long) res * a % MOD);
            }
            a = (int) ((long) a * a % MOD);
            b >>= 1;
        }
        return res;
    }

    static class Node {
        int index;
        // multiplier ^ x + y;
        int x;
        BigInteger y;
        int times;

        public Node(int index, int x, BigInteger y) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.times = 0;
        }
    }
}
