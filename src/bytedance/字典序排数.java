package bytedance;

import java.util.ArrayList;
import java.util.List;

public class 字典序排数 {
    /**
     * 返回[1, n]按照字典序排序的数
     * 为了实现空间复杂度为O(1)，不应该采用递归的方法求解
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            ans.add(num);
            if (num * 10 <= n) {
                // 下一个字典序最小的数字
                num = num * 10;
            } else {
                // 如果num最后一位为9或者num已经等于n了
                // 把现在这一位舍掉
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return ans;
    }
}
