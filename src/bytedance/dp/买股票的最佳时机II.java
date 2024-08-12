package bytedance.dp;

public class 买股票的最佳时机II {
    /**
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = 0;
        int buy = prices[0];
        int sell = prices[0];
        for (int i = 1; i < n; i++) {
            // 如果prices[i]大于当前的售价，则更新sell
            if (prices[i] > sell) {
                sell = prices[i];
                // 小于，此时可以进行售卖了.
            } else if (prices[i] <= sell) {
                res += sell - buy;
                buy = prices[i];
                sell = prices[i];
            }
        }
        res += sell - buy;
        return res;
    }

    public int maxProfitDp(int[] prices) {
        int n = prices.length;
        // dp[i][0]代表持有一只股票的最大利润， dp[i][1]代表没有股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
