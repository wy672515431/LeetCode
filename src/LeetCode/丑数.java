package LeetCode;

import java.util.HashSet;
import java.util.PriorityQueue;
/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。

    丑数 就是只包含质因数 2、3 或 5 的正整数。
 */
public class 丑数 {
    public int nthUglyNumber(int n) {
        HashSet<Long> set = new HashSet();
        PriorityQueue<Long> queue = new PriorityQueue();
        set.add(1L);
        queue.offer(1L);
        long ans = 1;
        while (n > 0 && !queue.isEmpty()) {
            ans = queue.poll();
            if (!set.contains(ans * 2)) {
                set.add(ans * 2);
                queue.offer(ans * 2);
            }
            if (!set.contains(ans * 3)) {
                set.add(ans * 3);
                queue.offer(ans * 3);
            }
            if (!set.contains(ans * 5)) {
                set.add(ans * 5);
                queue.offer(ans * 5);
            }
            n--;
        }
        return (int)ans;
    }

    /**
     * 动态规划
     * 维护三个数组,每个数组分别是2,3,5整除的递增数组，维护一个严格的递增数组
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num1 = dp[p2] * 2, num2 = dp[p3] * 3, num3 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num1, num2), num3);
            if (dp[i] == num1) {
                p2++;
            }  if (dp[i] == num2) {
                p3++;
            }  if (dp[i] == num3) {
                p5++;
            }
        }
        return dp[n];
    }
}
