package bytedance.dp;

public class 摆动序列 {
    public int wiggleMaxLength(int[] nums) {
        // 抽象两个状态 上升和下降
        int n = nums.length;
        int[][] dp = new int[n][2];
        int ans = 1;
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                } else if (nums[i] < nums[j]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                }
            }
            ans = Math.max(ans, Math.max(dp[i][1], dp[i][0]));
        }
        return ans;
    }

    public int optimized(int[] nums) {
        // up[i]代表[0 .. i]中最后两个数字递增的最长摆动序列长度
        // down[i]代表[0 .. i]中最后两个数字递减的最长摆动序列长度
        // nums[i + 1] > nums[i], 此时若递减的最后下标为i，则up[i] = down[i] + 1, 如果为j < i,则[j + 1, i]递增，up[i] = down[i] + 1
        // nums[i + 1] < nums[i] down[i] = up[i] + 1

        // 初始值均为1
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
