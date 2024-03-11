package bytedance.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 单词拆分
{
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            // 字符串[0..i]是否包含
            boolean[] dp = new boolean[s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j <= i; j++) {
                    String substr = s.substring(j, i + 1);
                    if (j == 0 && wordDictSet.contains(substr)) {
                        dp[i] = true;
                    } else if (j != 0 && wordDictSet.contains(substr)) {
                        dp[i] = dp[i] || dp[j - 1];
                    }
                }
            }
            return dp[s.length() - 1];
        }
}
