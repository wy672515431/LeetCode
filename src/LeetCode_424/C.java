package LeetCode_424;

public class C {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length - 1;
        int low = 0, high = m + 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isZeroArray(nums, queries, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isZeroArray(int[] nums, int[][] queries, int mid) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        // diff[i + 1] = nums[i] - nums[i - 1]
        // diff[1] = 0;
        for (int i = 0; i < mid; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];
            diff[l + 1] += val;
            if (r + 2 <= n) {
                diff[r + 2] -= val;
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
