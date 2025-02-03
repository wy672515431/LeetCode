package bytedance.math;

public class ExtGCD {
    // a * x + b * y = gcd(a, b)
    // b * x' + (a - b * floor(a / b)) * y' = a * x + b * y
    // a * (x - y') + b * (y - x' + y' * floor(a / b)) = 0;
    // x = y', y = x' - y' * floor(a / b)
    public static long exgcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        }
        long res = exgcd(b, a % b, x, y);
        long t = x[0];
        x[0] = y[0];
        y[0] = t - a / b * y[0];
        return res;
    }
}
