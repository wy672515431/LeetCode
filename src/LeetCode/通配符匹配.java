package LeetCode;

public class 通配符匹配 {
    /**
     * dp[i][j]表示s.substring(0,i + 1) 与 p.substring(0, j + 1)是否匹配
     * p[j]为字母  dp[i][j] = (s[i] == p[j]) && dp[i-1][j-1]
     * p[j]为问号  dp[i][j] = dp[i - 1][j - 1]
     * p[j]为*号   dp[i][j] = dp[i][j - 1] || dp[i - 1][j]
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= plen; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1)) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[slen][plen];
    }
}
