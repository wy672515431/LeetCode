package bytedance.dp.状态压缩dp;

import java.util.Arrays;

public class 并行课程II {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        // 每门课的前置课程
        int[] preCourse = new int[n];
        for (int i = 0; i < relations.length; i++) {
            preCourse[relations[i][1] - 1] |= (1 << (relations[i][0] - 1));
        }
        int[] pre = new int[1 << n];
        // 遍历每个状态,它们的前置课程
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    pre[i] |= preCourse[j];
                }
            }
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 0; i < (1 << n); i++) {
            // sub是要上的课，要保证它的前置课程都上完了
            //
            for (int sub = i; sub != 0; sub = (sub - 1) & i) {
                if (Integer.bitCount(sub) <= k && (pre[sub] & (i ^ sub)) == pre[sub]){
                    dp[i] = Math.min(dp[i], dp[i ^ sub] + 1);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
