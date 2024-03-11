package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 全排列 {
    int n;
    boolean[] isVisited;
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> permutation = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        isVisited = new boolean[n];
        solve(nums, 0);
        return ans;
    }

    private void solve(int[] nums, int size) {
        if (size == n) {
            List<Integer> temp = new ArrayList<>(permutation);
            ans.add(temp);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                permutation.add(nums[i]);
                isVisited[i] = true;
                solve(nums, size + 1);
                isVisited[i] = false;
                permutation.removeLast();
            }
        }
    }

}
