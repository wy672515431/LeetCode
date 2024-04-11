package bytedance.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 单词拆分
{
    /**
     * 将s拆分成多个单词，每个单词都在wordDict中
     * @param s
     * @param wordDict
     * @return
     */
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            // 字符串[0..i]是否包含
            boolean[] dp = new boolean[s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j <= i; j++) {
                    // 最开始是完整的字符串
                    String substr = s.substring(j, i + 1);
                    // 如果包含完整字符串，dp[i] = true
                    if (j == 0 && wordDictSet.contains(substr)) {
                        dp[i] = true;
                    } else if (j != 0 && wordDictSet.contains(substr)) {
                        // 如果[j, i]字符串包含，则却决于dp[j - 1]是否能划分
                        dp[i] = dp[i] || dp[j - 1];
                    }
                }
            }
            return dp[s.length() - 1];
        }
}
