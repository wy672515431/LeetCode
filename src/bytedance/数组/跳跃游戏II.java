package bytedance.数组;

import java.util.Arrays;

public class 跳跃游戏II {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    public int optimizedJump(int[] nums) {
        int end = 0;
        int maxPos = 0;
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 代表从该跳点能跳到最远的地方
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                // 达到最远的地方了，我们必须重新跳了，不可能在这一次跳跃过程中达到后面的距离
                end = maxPos;
                ans++;
            }
        }
        return ans;
    }
}
