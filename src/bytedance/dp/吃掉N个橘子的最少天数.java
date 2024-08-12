package bytedance.dp;

import java.util.HashMap;
import java.util.Map;

public class 吃掉N个橘子的最少天数 {
    Map<Integer, Integer> memo = new HashMap<>();
    public int minDays(int n) {
        // n的值会很大，采用dp数组会超出内存限制
        // 记忆化搜索
        // 吃一个橘子记为操作1，吃 1 / 2 橘子记为操作2，吃 1 / 3 橘子记为操作3
        // 很容易得到以下性质：在任意一次操作2前，最多进行一次操作1. 在任意一次操作3前，最多进行两次操作1
        // f(i) = f(i / 2) + i % 2 + 1
        // f(i) = f(i / 3) + i % 3 + 1
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int res = Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3));
        memo.put(n, res);
        return memo.get(n);
    }
}
