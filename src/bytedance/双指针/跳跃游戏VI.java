package bytedance.双指针;

import java.util.ArrayDeque;
import java.util.Deque;

public class 跳跃游戏VI {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
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
            // 对于下标i,j，如果i < j 且 dp[i] < dp[j], 则没用
            while (!deque.isEmpty() && dp[deque.peekLast()] < dp[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return dp[n - 1];
    }
}
