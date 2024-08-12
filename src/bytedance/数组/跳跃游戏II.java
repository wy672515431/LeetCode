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
        // 我们从必须从0开始起跳，end = 0；
        // 下一次我们可以从[0 ~ 0 + nums[0]]中任意地方起跳
        int end = 0;
        // 当前一跳最远能到的地方，比如在0的时候，最远能够跳到[0 ~ 0 + nums[0]]的地方
        // 在上述区间的地方，都能通过一跳达到
        int maxPos = 0;
        // 跳的最小步数
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
