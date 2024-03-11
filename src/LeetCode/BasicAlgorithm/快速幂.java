package LeetCode.BasicAlgorithm;

import java.util.Map;

public class 快速幂 {
    // (a ^ b) % mod
    // 3 ^ 8 = 9 ^ 4 = 81 ^ 2 = 81 * 81
    public long solve(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                res = (int) (res * a % mod);
            }
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }

}
