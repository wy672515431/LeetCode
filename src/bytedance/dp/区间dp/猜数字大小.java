package bytedance.dp.区间dp;

public class 猜数字大小 {

    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }

    public static int getMoneyAmount(int n) {
        // given [i, j], assume that we choose k,  k >= i && k <= j
        // dp[i][j] = k + Math.max(dp[i][k - 1], dp[k + 1][j])
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n; i >= 1; i--) {
            for (int j = i + 1; j < n + 1; j++) {
                dp[i][j] = 0x3f3f3f3f;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
    }
}
