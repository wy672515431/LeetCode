package bytedance.dp;

public class 买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int buyPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buyPrice) {
                ans = Math.max(ans, prices[i] - buyPrice);
            }
            if (prices[i] < buyPrice) {
                buyPrice = prices[i];
            }
        }
        return ans;
    }
}
