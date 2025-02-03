package bytedance.dp;

public class 交错字符串 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (m + n != t) {
            return false;
        }
        // dp[i][j] 表示 s1 的前 i 个字符和 s2 的前 j 个字符是否能交错组成 s3 的前 i+j 个字符
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (s1.charAt(i - 1) == s3.charAt(p) && dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (s2.charAt(j - 1) == s3.charAt(p) && dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int i = 1; i <= m; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i] = dp[i - 1];
            } else {
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[m];
    }
}
