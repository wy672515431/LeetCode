package bytedance.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集II {
    // 出现重复
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> subsets = new ArrayList<>();
    boolean[] visited;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        solve(nums, 0);
        return res;
    }

    private void solve(int[] nums, int cur) {
        for (int i = cur; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            subsets.add(nums[i]);
            visited[i] = true;
            res.add(new ArrayList<>(subsets));
            solve(nums, i + 1);
            visited[i] = false;
            subsets.removeLast();
        }
    }
}
