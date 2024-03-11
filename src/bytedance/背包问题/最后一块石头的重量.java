package bytedance.背包问题;

import java.util.Arrays;

public class 最后一块石头的重量 {
    // 本质上这道题，是要将石头分为两堆，且两堆的重量差要小，接近于sum / 2;
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int n = stones.length;
        int[] dp = new int[sum / 2 + 1];
        for (int stone : stones) {
            for (int j = sum / 2; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[sum / 2];
    }
}
