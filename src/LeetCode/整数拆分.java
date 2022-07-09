package LeetCode;

public class 整数拆分 {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length];
        for (int i = 0; i < coins.length; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > i) {
                    if (j == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                } else if (coins[j] == i) {
                    if (j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + dp[i][j - 1];
                    }
                } else {
                    if (j == 0) {
                        dp[i][j] = dp[i - coins[j]][j];
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i - coins[j]][j];
                    }
                }
            }
        }
        return dp[amount][coins.length - 1];
    }

    /**
     * 不考虑顺序
     * @param amount
     * @param coins
     * @return
     */
    public int change1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 不考虑顺序
     * @param target
     * @param nums
     * @return
     */
    public int change2(int target, int[] nums) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

}