package bytedance.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> combs = new ArrayList<>();
    int n;
    int sum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        n = candidates.length;
        Arrays.sort(candidates);
        solve(candidates, target, 0);
        return ans;
    }

    private void solve(int[] candidates, int target, int index) {
        for (int i = index; i < n; i++) {
            sum += candidates[i];
            combs.add(candidates[i]);
            if (sum > target) {
                combs.removeLast();
                sum -= candidates[i];
                break;
            } else if (sum == target) {
                List<Integer> temp = new ArrayList<>(combs);
                ans.add(temp);
                combs.removeLast();
                sum -= candidates[i];
                break;
            } else {
                solve(candidates, target, i);
                combs.removeLast();
                sum -= candidates[i];
            }
        }
    }
}
