package LeetCode;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 */
public class 阶乘后的零 {
    public int trailingZeroes(int n) {
        int cnt = 0;
        for (int i = 5; i <= n; i += 5) {
            int cur = i;
            while (cur % 5 == 0) {
                cnt++;
                cur = cur / 5;
            }
        }
        return cnt;
    }

    /***
     *  ... * (1 * 5) * ... * (1 * 5 * 5) * ... * (2 * 5 * 5) * ... * (3 * 5 * 5) * ... * n
     *需要每次除5看有多少
     * @param n
     * @return
     */
    public int trailingZeroes1(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }
}
