package LeetCode_419;

import java.util.HashMap;
import java.util.Map;

public class C {
    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.countWinningSequences("FFW"));
    }
    private static final int MOD = 1000000007;
    Map<Character, Integer> map = new HashMap<>() {{
        put('F', 0);
        put('W', 1);
        put('E', 2);
    }};
    Map<Character, Character> losingMap = new HashMap<>() {{
        put('F', 'E');
        put('W', 'F');
        put('E', 'W');
    }};
    Map<Character, Character> winningMap = new HashMap<>() {{
        put('F', 'W');
        put('W', 'E');
        put('E', 'F');
    }};
    public int countWinningSequences(String s) {
        // F输给W，W输给E，E输给F
        int n = s.length();
        // 总分严格大于
        // dp[i] += dp[i - 1] 这次平局
        // 如果这次赢，取上次平局或者赢
        // 如果这次输，取上次赢超过一分
        int[][][] dp = new int[n + 1][2 * n + 1][3];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 2 * n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (i == 0) {
                // 平局
                dp[i + 1][n][map.get(ch)] = 1;
                // 赢
                dp[i + 1][n + 1][map.get(winningMap.get(ch))] = 1;
                // 输
                dp[i + 1][n - 1][map.get(losingMap.get(ch))] = 1;
            } else {
                // 平局
                for (int j = 0; j <= 2 * n; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (k != map.get(ch) && dp[i][j][k] != Integer.MIN_VALUE) {
                            if (dp[i + 1][j][map.get(ch)] == Integer.MIN_VALUE) {
                                dp[i + 1][j][map.get(ch)] = 0;
                            }
                            dp[i + 1][j][map.get(ch)] = (dp[i + 1][j][map.get(ch)] + dp[i][j][k]) % MOD;
                        }
                        if (k != map.get(winningMap.get(ch)) && j < 2 * n && dp[i][j][k] != Integer.MIN_VALUE) {
                            if (dp[i + 1][j + 1][map.get(winningMap.get(ch))] == Integer.MIN_VALUE) {
                                dp[i + 1][j + 1][map.get(winningMap.get(ch))] = 0;
                            }
                            dp[i + 1][j + 1][map.get(winningMap.get(ch))] = (dp[i + 1][j + 1][map.get(winningMap.get(ch))] + dp[i][j][k]) % MOD;
                        }
                        if (k != map.get(losingMap.get(ch)) && j > 0 && dp[i][j][k] != Integer.MIN_VALUE) {
                            if (dp[i + 1][j - 1][map.get(losingMap.get(ch))] == Integer.MIN_VALUE) {
                                dp[i + 1][j - 1][map.get(losingMap.get(ch))] = 0;
                            }
                            dp[i + 1][j - 1][map.get(losingMap.get(ch))] = (dp[i + 1][j - 1][map.get(losingMap.get(ch))] + dp[i][j][k]) % MOD;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = n + 1; i <= 2 * n; i++) {
            for (int j = 0; j < 3; j++) {
                if (dp[n][i][j] != Integer.MIN_VALUE) {
                    ans = (ans + dp[n][i][j]) % MOD;
                }
            }
        }
        return ans;
    }
}
