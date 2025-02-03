package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 子集 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> subsets = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        // empty set
        res.add(new ArrayList<>());
        solve(nums, 0);
        return res;
    }

    public List<List<Integer>> subsetsWithBit(int[] nums) {
        // 我们可以用位运算来解决这个问题
        // 0 0 0 每一位代表数组中的一个元素是否出现在子集中
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            subsets.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subsets.add(nums[i]);
                }
            }
            res.add(new ArrayList<>(subsets));
        }
        return res;
    }

    private void solve(int[] nums, int cur) {
        for (int i = cur; i < nums.length; i++) {
            subsets.add(nums[i]);
            res.add(new ArrayList<>(subsets));
            solve(nums, i + 1);
            subsets.removeLast();
        }
    }
}
