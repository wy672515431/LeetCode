package bytedance.双指针;

import java.util.ArrayDeque;
import java.util.Deque;

public class 跳跃游戏VI {
    /**
     * 每一个格子有积分，每一次最多可以跳k步，求最多的积分
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // dp[i]代表到达i的最大积分
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        for (int i = 1; i < n; i++) {
            // 超过k步
            while (deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            dp[i] = dp[deque.peekFirst()] + nums[i];
            // 如果dp[i] > dp[deque.peekLast()]，那么deque.peekLast()就没有存在的必要了
            // 因为dp[i]显然对后面的影响更大.
            while (!deque.isEmpty() && dp[deque.peekLast()] < dp[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return dp[n - 1];
    }
}
