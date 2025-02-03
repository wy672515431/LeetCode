package LeetCode_421;

import java.util.List;

public class D {
    private static final int MOD = 1000000007;
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        long ans = 0;
        long[] dp = new long[26];
        long[] preSum = new long[27];
        for (int i = 0; i < 26; i++) {
            dp[i] = 1;
            preSum[i + 1] = preSum[i] + dp[i];
        }
        long[] pre = new long[26];
        for (int i = 1; i <= t; i++) {
            for (int j = 25; j >= 0; j--) {
                int num = nums.get(j);
//                for (int k = 1; k <= num; k++) {
//                    pre[j] = (pre[j] + dp[(j + k) % 26]) % MOD;
//                }
                // [j + 1, Math.max(j + num, 26)], [0 ~ (j + num) % 26]
                pre[j] = (preSum[Math.min(j + num + 1, 26)] - preSum[j + 1] + MOD) % MOD;
                if (j + num >= 26) {
                    pre[j] = (pre[j] + preSum[(j + num) % 26]) % MOD;
                }
            }
            for (int j = 0; j < 26; j++) {
                dp[j] = pre[j];
                pre[j] = 0;
                preSum[j + 1] = preSum[j] + dp[j];
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ans = (ans + dp[ch - 'a']) % MOD;
        }
        return (int) (ans % MOD);
    }
}
