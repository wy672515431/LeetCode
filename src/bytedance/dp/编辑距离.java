package bytedance.dp;

import java.util.Arrays;

public class 编辑距离 {
    /**
     * 将word1转变为word2
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        // 插入、删除、替换
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < len1; i++) {
            char ch1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char ch2 = word2.charAt(j);
                if (ch1 == ch2) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    // 插入、删除、替换 dp[i][j] 替换， dp[i][j + 1]代表A插入，dp[i + 1][j]代表B插入
                    dp[i + 1][j + 1] = Math.min(
                            dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])
                    ) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
