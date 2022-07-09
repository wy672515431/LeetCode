package LeetCode;

import java.util.HashMap;

public class 最长的斐波那契子序列长度 {
    /**
     * 对于arr[i] > arr[j] > arr[k]
     * 有 i > j > k
     * 假设我们确定arr[i] 与 arr[j]为斐波那契数列的后两项,
     * 则arr[i] - arr[j] = arr[k]
     * dp[j][i]代表后两项为arr[j],arr[i]的斐波那契序列长度
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }
        int[][] dp = new int[len][len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            //保证arr[j] > arr[k]
            for (int j = i - 1; j >= 0 && arr[i]  < 2 * arr[j]; j--) {
                int k = map.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }
}
