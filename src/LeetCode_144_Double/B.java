package LeetCode_144_Double;

import java.util.Arrays;

public class B {

    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int n = s.length();
        long[] nextCostPreSum = new long[27];
        long[] previousCostPreSum = new long[27];
        for (int i = 0; i < 26; i++) {
            nextCostPreSum[i + 1] = nextCostPreSum[i] + nextCost[i];
            previousCostPreSum[i + 1] = previousCostPreSum[i] + previousCost[i];
        }
        long[][] dp = new long[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = 0;
        for (int i = 0; i < n; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            long cost = 0;
            long cost1 = 0;
            if (ch2 != ch1) {
                if (ch2 > ch1) {
                    cost = nextCostPreSum[ch2 - 'a'] - nextCostPreSum[ch1 - 'a'];
                    cost1 = previousCostPreSum[ch1 - 'a' + 1] + previousCostPreSum[26] - previousCostPreSum[ch2 - 'a' + 1];
                } else {
                    cost = nextCostPreSum[26] - nextCostPreSum[ch1 - 'a'] + nextCostPreSum[ch2 - 'a'];
                    cost1 = previousCostPreSum[ch1 - 'a' + 1] - previousCostPreSum[ch2 - 'a' + 1];
                }
            }
            dp[i + 1][0] = Math.min(dp[i][0], dp[i][1]) + cost;
            dp[i + 1][1] = Math.min(dp[i][0], dp[i][1]) + cost1;
        }
        return Math.min(dp[n][0], dp[n][1]);
    }
}
