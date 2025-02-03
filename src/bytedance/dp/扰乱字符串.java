package bytedance.dp;

import java.util.HashMap;
import java.util.Map;

public class 扰乱字符串 {
    // 使用记忆化搜索优化dp
    // memo[i][j][len] 表示s1从i开始，s2从j开始，长度为len的字符串是否为扰乱字符串
    // 0表示未计算，1表示是，-1表示不是
    int[][][] memo;
    String s1, s2;
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        this.memo = new int[n][n][n + 1];
        return solve(0, 0, n);
    }

    private boolean solve(int i, int j, int len) {
        if (memo[i][j][len] == 1) {
            return true;
        }
        if (memo[i][j][len] == -1) {
            return false;
        }
        // 两个子串相等
        if (s1.substring(i, i + len).equals(s2.substring(j, j + len))) {
            memo[i][j][len] = 1;
            return true;
        }
        if (!check(i, j, len)) {
            memo[i][j][len] = -1;
            return false;
        }
        // 枚举分割位置
        for (int x = 1; x < len; x++) {
            if (solve(i, j, x) && solve(i + x, j + x, len - x)) {
                memo[i][j][len] = 1;
                return true;
            }
            if (solve(i, j + len - x, x) && solve(i + x, j, len - x)) {
                memo[i][j][len] = 1;
                return true;
            }
        }
        memo[i][j][len] = -1;
        return false;
    }

    private boolean check(int i, int j, int len) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int x = i; x < i + len; x++) {
            countMap.put(s1.charAt(x), countMap.getOrDefault(s1.charAt(x), 0) + 1);
        }
        for (int x = j; x < j + len; x++) {
            char ch = s2.charAt(x);
            if (!countMap.containsKey(ch)) {
                return false;
            }
            countMap.put(ch, countMap.get(ch) - 1);
        }
        for (int count : countMap.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
