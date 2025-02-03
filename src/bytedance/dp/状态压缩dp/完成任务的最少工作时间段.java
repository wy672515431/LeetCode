package bytedance.dp.状态压缩dp;

import java.util.Arrays;

public class 完成任务的最少工作时间段 {
    /**
     * state来表示任务的完成状态，每一位代表一个任务，1表示完成，0表示未完成
     *
     * @param tasks
     * @param sessionTime
     * @return
     */
    public int minSessions(int[] tasks, int sessionTime) {
        // n >= 1 && n <= 14
        int n = tasks.length;
        int[] sum = new int[1 << n];
        // 遍历所有可能的状态
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum[i] += tasks[j];
                }
            }
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < (1 << n); i++) {
            // 子状态 sub表示在这个工作时段需要完成的任务
            for (int sub = i; sub != 0; sub = (sub - 1) & i) {
                if (sum[sub] <= sessionTime) {
                    // i ^ sub 差集
                    dp[i] = Math.min(dp[i], dp[i ^ sub] + 1);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
