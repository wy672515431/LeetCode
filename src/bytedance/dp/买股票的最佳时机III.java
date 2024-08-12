package bytedance.dp;

public class 买股票的最佳时机III {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(new 买股票的最佳时机III().maxProfit(prices));
    }

    /**
     * 最多完成两笔交易
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        return maxProfit(prices, 2);
    }

    /**
     * 最多完成k笔交易
     *
     * @param prices
     * @param k
     * @return
     */
    public int maxProfit(int[] prices, int k) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k];
        for (int i = 0; i < k; i++) {
            dp[0][0][i] = -prices[0];
            dp[0][1][i] = 0;
        }
        for (int i = 1; i < n; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], -prices[i]);
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][0] + prices[i]);
            for (int j = 1; j < k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j - 1] - prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j] + prices[i]);
            }
        }
        return Math.max(dp[n - 1][0][k - 1], dp[n - 1][1][k - 1]);
    }
}
