package bytedance.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 全排列II {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> permutation = new ArrayList<>();
    boolean[] isVisited;
    int n;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n = nums.length;
        isVisited = new boolean[n];
        Arrays.sort(nums);
        solve(nums);
        return ans;
    }

    private void solve(int[] nums) {
        if (permutation.size() == n) {
            ans.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 1.当前数字被访问过
            // 2.当前数字和前一个数字相同，且前一个数字没有被访问过
            // 当前一个数字相同的时候，只有先访问前面的数字，才能访问后面的数字
            // 这样可以避免重复
            if (isVisited[i] || (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1])) {
                continue;
            }
            isVisited[i] = true;
            permutation.add(nums[i]);
            solve(nums);
            isVisited[i] = false;
            permutation.removeLast();
        }
    }
}
