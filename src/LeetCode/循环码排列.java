package LeetCode;

import LeetCode.BitOperation.BitUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class 循环码排列 {
    List<Integer> ans = new ArrayList<>();
    HashSet<Integer> set = new HashSet<>();
    public List<Integer> circularPermutation(int n, int start) {
        solve(start, n);
        return ans;
    }

    /**
     *
     * @param cur 当前的数
     * @param n 代表数最多有几位
     */
    public void solve(int cur ,int n) {
        ans.add(cur);
        set.add(cur);
        for (int i = 0; i < n; i++) {
            cur ^= (1 << i);
            if (!set.contains(cur)) {
                solve(cur, n);
            }
        }
    }
}
