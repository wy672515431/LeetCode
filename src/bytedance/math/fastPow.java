package bytedance.math;

public class fastPow {
    public double myPow(double x, int n) {
        // 考虑负数转为正数，溢出的情况
        return (long) n >= 0 ? fastPow(x, n) : 1.0 / fastPow(x, -(long) n);
    }

    /**
     * 快速幂
     * @param x
     * @param n n >= 0
     * @return
     */
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double res = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            x *= x;
            n >>>= 1;
        }
        return res;
    }
}
