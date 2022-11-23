package LeetCode_91_Double;

public class B {
    public static final int MOD = (int)1e9 + 7;
    public int countGoodStrings(int low, int high, int zero, int one) {
        // dp[i]表示长度为i的字符串应用1和0所用的方法数
        // dp[i] = dp[i - one] + 1
        int[] dp = new int[high + 1];
        dp[0] = 1;
        for (int i = 0; i <= high; i++) {
            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
            if (i >= zero) {
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }
        }
        int ans = 0;
        for (int i = low; i <= high; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        return ans;
    }
}
