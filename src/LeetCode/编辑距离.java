package LeetCode;
public class 编辑距离 {
    /**
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
        你可以对一个单词进行如下三种操作：
        插入一个字符 dp[i][j] = dp[i][j - 1] + 1
        删除一个字符 dp[i][j] = dp[i - 1][j] + 1
        替换一个字符 dp[i][j] = dp[i - 1][j - 1] + 1
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            dp[i + 1][0] = i + 1;
        }
        for (int i = 0; i < word2.length(); i++) {
            dp[0][i + 1] = i + 1;
        }
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                //二者相同
                int isEqual = (word1.charAt(i) == word2.charAt(j)) ? 0 : 1;
                dp[i + 1][j + 1] = Math.min(dp[i][j + 1] + 1, Math.min(dp[i + 1][j] + 1, dp[i][j] + isEqual));
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
