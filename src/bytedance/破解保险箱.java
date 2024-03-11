package bytedance;

import LeetCode.正则表达式匹配;

import java.util.HashSet;
import java.util.Set;

public class 破解保险箱 {
    // 一个字符串中包含密码的所有组合
    // n位密码，有k个数字，那么便有k^n种组合
    // n = 3, k = 2时
    // 000 可以转化为 000, 001
    // 我们将每个状态抽象为一个点，有k^(n - 1)个状态，每个点附带k条边，最后会形成k ^ n个组合
    // 每个节点的出度和入度都为k，则其度数为偶数，存在欧拉回路
    // 下面就是找到欧拉回路
    StringBuilder sb = new StringBuilder();
    int n, k, mod;
    Set<Integer> visit = new HashSet<>();
    public String crackSafe(int n, int k) {
        this.n = n;
        this.k = k;
        this.mod = (int)Math.pow(10, n - 1);
        dfs(0);
        sb.append("0".repeat(n - 1));
        return sb.toString();
    }

    /**
     *
     * @param u 顶点
     */
    private void dfs(int u) {
        for (int i = 0; i < k; i++) {
            int edge = u * 10 + i;
            if (visit.contains(edge)) {
                continue;
            }
            visit.add(edge);
            // 去掉第一位
            dfs(edge % mod);
            sb.append(i);
        }
    }

    public static void main(String[] args) {
        破解保险箱 a = new 破解保险箱();
        a.crackSafe(3, 2);
    }
}
