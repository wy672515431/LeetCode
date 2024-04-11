package bytedance.dp;

import java.util.ArrayList;
import java.util.List;

public class 分割回文串 {
    int len;
    boolean[][] dp;
    List<List<String>> ans = new ArrayList<>();
    List<String> palindromes = new ArrayList<>();

    /**
     * 分割回文串, 将s分割成多个回文串
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        len = s.length();
        dp = new boolean[len][len];
        // 回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int i = len - 1; i >= 0; i--) {
            char ch1 = s.charAt(i);
            for (int j = i + 1; j < len; j++) {
                char ch2 = s.charAt(j);
                if (ch1 == ch2) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] || dp[i][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        solve(0, s);
        return ans;
    }

    /**
     *
     * @param start 分割起始位置
     */
    private void solve(int start, String s) {
        if (start == len) {
            List<String> temp = new ArrayList<>(palindromes);
            ans.add(temp);
            return;
        }
        for (int i = start; i < len; i++) {
            // 回文串
            if (dp[start][i]) {
                palindromes.add(s.substring(start, i + 1));
                solve(i + 1, s);
                palindromes.removeLast();
            }
        }
    }
}
