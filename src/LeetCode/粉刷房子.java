package LeetCode;

public class 粉刷房子 {
    /**
     * 相邻的房子颜色不同
     * dp[i][0] --- 红色最低成本
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(dp[costs.length - 1][0], Math.min(dp[costs.length - 1][1],
                dp[costs.length - 1][2]));
    }
}
