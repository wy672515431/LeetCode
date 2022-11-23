package LeetCode;

public class 香槟塔 {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[100][100];
        dp[0][0] = poured * 250.0;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] > 250) {
                    dp[i + 1][j] += (dp[i][j] - 250) / 2.0;
                    dp[i + 1][j + 1] += (dp[i][j] - 250) / 2.0;
                    dp[i][j] = 250;
                }
            }
        }
        return dp[query_row][query_glass] / 250.0;
    }
}
