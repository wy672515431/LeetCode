package bytedance.背包问题;

import java.util.Arrays;

public class 零钱兑换 {
    public int coinChange(int[] coins, int amount) {
        //表示dp[i] 恰好组成i所需的最小硬币数, dp[0] = 0
        //dp[i] = Math.min(dp[i - k * coins[i]] + k)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for (int i = 0 ; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return (dp[amount] == 0x3f3f3f3f) ? -1 : dp[amount];
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount] == 0x3f3f3f3f ? 0 : dp[amount];
    }
}
