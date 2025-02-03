package bytedance.math;

public class 整数拆分 {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                int t1 = j * (i - j);
                int t2 = j * dp[i - j];
                int t3 = (i - j) * dp[j];
                int t4 = dp[j] * dp[i - j];
                dp[i] = Math.max(dp[i], Math.max(t1, Math.max(t2, Math.max(t3, t4))));
            }
        }
        return dp[n];
    }

    public int optimalIntegerBreak(int n) {
        // 我们假设最大乘积中存在一个因子f>=4,我们总是可以通过2和f-2来替换
        // 2 * (f - 2) = 2f - 4 >= f
        // 所以最佳乘积中只能包含2和3
        // 3 * 3 > 2 * 2 * 2
        // 2不要超过两个
        if (n <= 3) {
            return n - 1;
        }
        int div = n / 3;
        int mod = n % 3;
        if (mod == 0) {
            return (int) Math.pow(3, div);
        } else if (mod == 1) {
            return (int) Math.pow(3, div - 1) * 4;
        } else {
            return (int) Math.pow(3, div) * 2;
        }
    }
}
