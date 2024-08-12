package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 因子的组合 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> getFactors(int n) {
        // 去除重复的list
        return dfs(n, 2);
    }

    /**
     *
     * @param n
     * @param factor 下一次递归的起始因子，不应该小于factor
     * @return
     */
    private List<List<Integer>> dfs(int n, int factor) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = factor; i <= Math.sqrt(n); i++) {
            if (n % i == 0 && i >= factor) {
                List<List<Integer>> temp = dfs(n / i, i);
                List<Integer> list1 = new ArrayList<>();
                list1.add(i);
                list1.add(n / i);
                res.add(list1);
                for (List<Integer> list : temp) {
                    List<Integer> temp1 = new ArrayList<>();
                    temp1.add(i);
                    temp1.addAll(list);
                    res.add(temp1);
                }
            }
        }
        return res;
    }

    // 质因子分解算法
    private List<Integer> getPrimeFactors(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                res.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            res.add(n);
        }
        return res;
    }
}
