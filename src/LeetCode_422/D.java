package LeetCode_422;

public class D {
    public static void main(String[] args) {
        D obj = new D();
        System.out.println(obj.countBalancedPermutations("2433"));
    }

    private static final int MAX = 721;
    private static final int MOD = 1000000007;

    public int countBalancedPermutations(String num) {
        int n = num.length();
        int[] count = new int[10];
        // dp[i][j][k]代表从前i个数取j个放到偶数位，和为k的方案数
        // dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k - num[i - 1]]
        long[][][] dp = new long[n + 1][n + 1][MAX];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }
        int sum = 0;

        for (int i = 0; i < n; i++) {
            count[num.charAt(i) - '0']++;
            sum += num.charAt(i) - '0';
        }

        if (sum % 2 != 0) {
            return 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, n / 2); j++) {
                for (int k = 0; k < MAX; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k]) % MOD;
                    if (k >= num.charAt(i - 1) - '0') {
                        dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - 1][k - (num.charAt(i - 1) - '0')]) % MOD;
                    }
                }
            }
        }
        long ans = dp[n][n / 2][sum / 2];
        ans = (ans * factorial(n / 2)) % MOD;
        ans = (ans * factorial(n - n / 2)) % MOD;
        for (int i = 0; i < 10; i++) {
            ans = (ans * inv(factorial(count[i]))) % MOD;
        }
        return (int) (ans % MOD);
    }

    private long factorial(long n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res = (res * i) % MOD;
        }
        return res;
    }

    /**
     * 求逆元
     *
     * @param n
     * @return
     */
    private long inv(long n) {
        return quickPow(n, MOD - 2);
    }

    private long quickPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}
