package bytedance.math;

public class 两数相除 {
    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX = Integer.MAX_VALUE;
    private static final int LIMIT = MIN / 2;
    public int divide(int dividend, int divisor) {
        // 先处理一些特殊情况
        // 唯一的溢出情况
        if (dividend == MIN && divisor == -1) {
            return MAX;
        }
        // 我们将正数转为负数，因为负数范围比正数大
        boolean sign = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        int ans = 0;
        // dividend > divisor ans = 0
        while (dividend <= divisor) {
            // 不断倍增
            int temp = divisor;
            int count = -1;
            // 防止溢出
            while (temp >= LIMIT && count >= LIMIT && dividend - temp <= temp) {
                temp += temp;
                count += count;
            }
            dividend -= temp;
            ans += count;
        }

        return sign ? ans : -ans;
    }
}
