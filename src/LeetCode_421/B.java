package LeetCode_421;

import java.util.HashMap;
import java.util.Map;

public class B {
    private static final int MOD = 1000000007;
    public int lengthAfterTransformations(String s, int t) {
        long ans = 0;
        long[][] dp = new long[26][t + 1];
        for (int i = 0; i < 26; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= t; i++) {
            for (int j = 0; j < 26; j++) {
                if (j == 25) {
                    dp[j][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
                } else {
                    dp[j][i] = dp[j + 1][i - 1];
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ans = (ans + dp[ch - 'a'][t]) % MOD;
        }
        return (int) (ans % MOD);
    }
}
