package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 组合 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> combs = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        solve(n, k, 0, 1);
        return ans;
    }

    private void solve(int n, int k, int cur, int index) {
        if (cur == k) {
            List<Integer> temp = new ArrayList<>(combs);
            ans.add(temp);
            return;
        }
        for (int i = index; i <= n; i++) {
            combs.add(i);
            solve(n, k, cur + 1, i + 1);
            combs.removeLast();
        }
    }
}
