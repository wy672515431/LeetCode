package LeetCode_413;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C {
    int n, m;
    int ans = 0;
    int sum = 0;
    Set<Integer> valSet = new HashSet<>();
    public int maxScore(List<List<Integer>> grid) {
        n = grid.size();
        m = grid.getFirst().size();
        dfs(0, grid);
        return ans;
    }

    /**
     * 当前的矩阵行数
     * @param cur
     */
    private void dfs(int cur, List<List<Integer>> grid) {
        if (cur == n) {
            ans = Math.max(ans, sum);
            return;
        }
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            int val = grid.get(cur).get(i);
            if (valSet.contains(val)) {
                continue;
            }
            valSet.add(val);
            sum += val;
            dfs(cur + 1, grid);
            sum -= val;
            valSet.remove(val);
            flag = false;
        }
        if (flag) {
            dfs(cur + 1, grid);
        }
    }
}
