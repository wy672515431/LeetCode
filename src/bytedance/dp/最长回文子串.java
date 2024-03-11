package bytedance.dp;

public class 最长回文子串 {
    public String longestPalindrome(String s) {
        //dp[i][j]为true,表示子串i,j为回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLength = 1;
        String ans = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        // 必须反向遍历，因为dp[i][j]需要dp[i + 1][j - 1]的值，所以不能正向遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] || dp[i][j];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;
    }
}
