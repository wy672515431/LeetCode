package LeetCode_414;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class C {
    public long findMaximumScore(List<Integer> nums) {
        // dp[i]代表跳到第i个位置的最大分数
        // dp[i] = max(dp[j] + (i - j) * nums[j])   j >= 0 && j < i
        int n = nums.size();
        long[] dp = new long[n];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[deque.peekFirst()] + (long) (i - deque.peekFirst()) * nums.get(deque.peekFirst());
            while (!deque.isEmpty() && dp[deque.peekLast()]
                    + (long) (i + 1 - deque.peekLast()) * nums.get(deque.peekLast()) < dp[i] + nums.get(i)) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return dp[n - 1];
    }
}
