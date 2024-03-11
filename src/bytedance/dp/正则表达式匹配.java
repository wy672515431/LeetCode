package bytedance.dp;

public class 正则表达式匹配 {
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        // 两个空串能够匹配
        dp[0][0] = true;
        for (int i = 0; i < plen; i++) {
            char ch = p.charAt(i);
            if (ch == '*') {
                // 丢弃该组合，不进行匹配
                dp[0][i + 1] = dp[0][i - 1];
            }
        }
        for (int i = 0; i < slen; i++) {
            char ch1 = s.charAt(i);
            for (int j = 0; j < plen; j++) {
                char ch2 = p.charAt(j);
                if (ch2 == '*') {
                    assert (j >= 1);
                    // 要向前看一个字符
                    char preCh = p.charAt(j - 1);
                    if (preCh == '.' || preCh == s.charAt(i)) {
                        // 匹配s当前的字符，该组合继续匹配
                        dp[i + 1][j + 1] = dp[i][j + 1];
                    }
                    // 丢弃该组合，不进行匹配
                    dp[i + 1][j + 1] = dp[i + 1][j + 1] || dp[i + 1][j - 1];
                } else if (ch2 == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    if (ch1 == ch2) {
                        dp[i + 1][j + 1] = dp[i][j];
                    } else {
                        dp[i + 1][j + 1] = false;
                    }
                }
            }
        }
        return dp[slen][plen];
    }
}
