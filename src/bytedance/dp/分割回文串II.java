package bytedance.dp;

import java.util.Arrays;

public class 分割回文串II {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
        for (int i = n - 1; i >= 0; i--) {
            char ch1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char ch2 = s.charAt(j);
                if (ch1 == ch2) {
                    dp[i][j] = (j - i == 1) || dp[i + 1][j - 1];
                }
            }
        }
        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = -1;
        res[1] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j][i]) {
                    res[i + 1] = Math.min(res[i + 1], res[j] + 1);
                }
            }
        }
        return res[n];
    }
}
