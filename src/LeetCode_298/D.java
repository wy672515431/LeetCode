package LeetCode_298;

public class D {
    /**
     * 本题有三种情况
     * 对于高为x,y的木块
     * 1.如果prices中存在对应，可以直接卖出
     * 2.如果x>1，则可以沿水平切割
     * 3.如果y>1，则可以沿竖直切割
     * @param m
     * @param n
     * @param prices
     * @return
     */
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] price : prices) {
            dp[price[0]][price[1]] = price[2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //水平切割
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - k][j] + dp[k][j]);
                }
                //竖直切割
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - k] + dp[i][k]);
                }
            }
        }
        return dp[m][n];
    }
}
