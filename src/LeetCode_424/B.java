package LeetCode_424;

public class B {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        // diff[i + 1] = nums[i] - nums[i - 1]
        // diff[1] = 0;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            diff[l + 1] += 1;
            if (r + 2 <= n) {
                diff[r + 2] -= 1;
            }
        }
        int sum = diff[0];
        for (int i = 1; i <= n; i++) {
            sum = diff[i] + sum;
            if (sum < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
