package LeetCode;

public class _878 {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * 返回第n个能被a和b整除的数
     * f(x)代表小于等于x神奇数字的个数
     * f(x) = x / a + x / b - x / c
     *
     * @param n
     * @param a
     * @param b
     * @return
     */
    public int nthMagicalNumber(int n, int a, int b) {
        long low = Math.min(a, b);
        long high = (long) n * Math.min(a, b);
        long mid;
        int c = lcm(a, b);
        while (low < high) {
            mid = (high - low) / 2 + low;
            long num = mid / a + mid / b - mid / c;
            if (num < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (int)(low % MOD);
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
