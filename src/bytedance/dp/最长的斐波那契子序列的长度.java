package bytedance.dp;

import java.util.HashMap;
import java.util.Map;

public class 最长的斐波那契子序列的长度 {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        // dp[i][j] 表示以 arr[i] 和 arr[j] 为最后两个元素的斐波那契子序列的长度
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // arr[k] + arr[j] = arr[i]
            // arr[k] < arr[j] < arr[i]
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = index.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[j][i], Math.max(dp[k][j] + 1, 3));
                    ans = Math.max(ans, dp[j][i]);
                }
            }
        }
        return ans;
    }
}
