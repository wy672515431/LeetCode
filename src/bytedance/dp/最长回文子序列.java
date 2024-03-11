package bytedance.dp;

public class 最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch1 = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                char ch2 = s.charAt(j);
                if (ch1 == ch2) {
                    if (j - i == 1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    if (j - i == 1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }
            }
        }
        return dp[len - 1][len - 1];
    }
}
