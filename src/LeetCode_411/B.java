package LeetCode_411;

public class B {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] dp = new long[n][2];
        // dp[i][0]代表第一天喝A的最大能量，dp[i][1]代表第一天喝B的最大能量
        dp[0][0] = energyDrinkA[0];
        dp[0][1] = energyDrinkB[0];
        dp[1][0] = energyDrinkA[0] + energyDrinkA[1];
        dp[1][1] = energyDrinkB[0] + energyDrinkB[1];
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1]) + energyDrinkA[i];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0]) + energyDrinkB[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
