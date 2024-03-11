package bytedance;

public class 阶乘后的0 {
    /*
    public int trailingZeroes(int n) {
        // 5 25 125
        int ans = 0;
        int power = 0;
        int temp = n;
        // log(n)
        while (temp > 0) {
            temp = temp / 5;
            if (temp > 0) {
                power++;
            }
        }
        ans += n / 5;
        for (int i = 2; i <= power; i++) {
            int num = n / ((int)Math.pow(5, i));
            ans += (i - 1) * (num - num / 5);
        }
        return ans;
    }
     */

    /**
     * 2 * 5 = 10，才会在末尾贡献 0
     * 答案为 n! 中因子 2 的数量和因子 5 的数量的最小值
     * 阶乘中因子 5 的数量一定小于因子 2 的数量
     * 先从 1 - n 中的： 1 * 5, 2 * 5, ..., x * 5 每个拿出一个因子 5，有 x = n / 5 个数可以拿出
     * 拿完后剩下 1, 2, 3, ..., x; 重复上一步
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0){
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
