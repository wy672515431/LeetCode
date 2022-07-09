package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态转移方程 dp[k][n] 表示目前k个鸡蛋,n层楼所需的最小次数。
 * 如果我们仍一枚鸡蛋，鸡蛋要么碎，要么不碎。
 * dp[k][n] = min{max{dp[k - 1][i], dp[k][n - i]}}
 * 其中dp[k - 1][i]是关于i的增函数，n - i是关于i的减函数。
 * 在二者交点取到最大值的最小值。
 */
public class 鸡蛋掉落 {
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int superEggDrop(int k, int n) {
        return dp(k, n);
    }

    public int dp(int k, int n) {
        if (!memo.containsKey(n * 100 + k)) {
            int ans;
            if (n == 0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(k - 1, x - 1);
                    int t2 = dp(k, n - x);

                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)), Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
            }

            memo.put(n * 100 + k, ans);
        }

        return memo.get(n * 100 + k);
    }   
}
