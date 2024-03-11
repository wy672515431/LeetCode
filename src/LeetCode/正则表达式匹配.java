package LeetCode;

public class 正则表达式匹配 {
    /**
     * dp[i][j]表示s[0~i]是否能匹配p[0-j]
     * p[j]为单个字母
     * @param s 匹配串
     * @param p 模式串
     * @return
     */
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        // s和p为空时，初始值为true
        dp[0][0] = true;
        // 当s为空时，我们要考虑*的效果：0或多个前面的字符
        for (int i = 0; i < plen; i++) {
            char ch = p.charAt(i);
            if (ch == '*') {
                assert (i >= 1);
                dp[0][i + 1] = dp[0][i - 1];
            }
        }
        // 当字符为单词时，结论显然
        // 当字符为.时，结论显然
        // 当字符为*时，我们需要考虑前一个字符，如果前一个字符为.或者与s[i]相同
        // 我们需要三种情况
        // 1.dp[i + 1][j + 1] |= dp[i][j] 通配字符匹配当前字符 -> aa -> a*
        // 2.dp[i + 1][j + 1] |= dp[i + 1][j] 过滤掉当前通配字符 a -> a*
        // 3.dp[i + 1][j + 1] |= dp[i][j + 1] 通配符继续匹配 aaa -> a*

        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < plen; j++) {
                char ch = p.charAt(j);
                if (ch == '*') {
                    assert (j >= 1);
                    char pre = p.charAt(j - 1);
                    if (pre == '.' || pre == s.charAt(i)) {
                        dp[i + 1][j + 1] = dp[i][j] | dp[i + 1][j] | dp[i][j + 1];
                    }
                    // 不相等直接忽略前方字符和*
                    dp[i + 1][j + 1] |= dp[i + 1][j - 1];
                } else if (ch == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    if (ch == s.charAt(i)) {
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
