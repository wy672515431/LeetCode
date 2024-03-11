package LeetCode;

public class 目标和 {
    int ans = 0;
    public int findTargetSumWays(int[] nums, int target) {
        solve(nums, 0, 0, target);
        return ans;
    }

    public void solve(int[] nums, int cur, int sum, int target) {
        if (cur == nums.length) {
            if (sum == target) {
                ans++;
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                solve(nums, cur + 1, sum + nums[cur], target);
            } else {
                solve(nums, cur + 1, sum - nums[cur], target);
            }
        }
    }
}
