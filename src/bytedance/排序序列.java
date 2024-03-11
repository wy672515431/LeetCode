package bytedance;

import java.util.HashSet;
import java.util.Set;

public class 排序序列 {
    /**
     * 第一个数开头为1的，有 (n - 1)!
     * 确定第一个数组，第二个数为1开头的有(n - 2)！
     * 一次类推到最后一个数
     *
     * @param n 全排列
     * @param k 第k个全排列
     * @return 第k个全排列
     */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        // 1 ~ n - 1
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        Set<Integer> visit = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            int digit = (k - 1) / factorial[i] + 1;
            int count = 0;
            for (int j = 1; j <= 9; j++) {
                if (visit.contains(j)) {
                    continue;
                }
                count++;
                if (count == digit) {
                    sb.append(j);
                    visit.add(j);
                    break;
                }
            }
            k = k - (digit - 1) * factorial[i];
        }
        return sb.toString();
    }
}
