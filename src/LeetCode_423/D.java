package LeetCode_423;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D {
    private static final int MOD = 1000000007;
    public static void main(String[] args) {
        D d = new D();
        System.out.println(d.countKReducibleNumbers("1000", 2));
    }
    public int countKReducibleNumbers(String s, int k) {
        if (s.length() == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= 800; i++) {
            int count = calc(i);
            List<Integer> list = map.getOrDefault(count, new ArrayList<>());
            list.add(i);
            map.put(count, list);
        }
        int n = s.length();
        // C(n, k) = C(n-1, k-1) + C(n-1, k)
        long[][] dp = new long[n + 1][801];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= 800; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        long[] fac = new long[n + 1];
        fac[1] = 1;
        for (int i = 2; i <= n; i++) {
            fac[i] = (fac[i - 1] * 2) % MOD;
        }

        long res = 0;
        for (int i = 1; i <= k; i++) {
            for (int j : map.get(i)) {
                if (j <= n) {
                    res = (res + dp[n][j]) % MOD;
                }
            }
        }
        // 找到s的最后一个1的位置
        int lastIndex = s.lastIndexOf('1');
        assert (lastIndex != -1);
        if (lastIndex != n - 1) {
            res = (res - fac[n - lastIndex - 1] + 1 + MOD) % MOD;
        }
        // 检测s是否是一个可约数
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        if (calc(count) <= k) {
            res = (res - 1) % MOD;
        }
        return (int) (res % MOD);
    }

    /**
     *
     * @param n 二进制中1的个数，最多为800个
     * @return 需要多少次操作，变为1
     */
    private int calc(int n) {
        if (n == 1) {
            return 1;
        }
        int count = Integer.bitCount(n);
        return 1 + calc(count);
    }
}
