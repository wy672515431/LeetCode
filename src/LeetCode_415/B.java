package LeetCode_415;

public class B {
    public static void main(String[] args) {
        B obj = new B();
        int[] a = {3, 2, 5, 6};
        int[] b = {2, -6, 4, -5, -3, 2, -7};
        System.out.println(obj.maxScore(a, b));
    }

    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long[][] dp = new long[n][5];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 4; j++) {
                dp[i][j] = Long.MIN_VALUE;
            }
        }
        //dp[i][j] - 0 ~ i 选 j 个 的最大值
        dp[0][1] = (long) a[0] * b[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 4; j++) {
                if (i < j - 1) {
                    break;
                }
                // i >= j - 1
                // 选自己
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + (long) a[j - 1] * b[i]);
                // 不选自己
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp[n - 1][4];
    }
}
