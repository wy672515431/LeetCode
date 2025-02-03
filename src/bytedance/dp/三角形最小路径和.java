package bytedance.dp;

import java.util.List;

public class 三角形最小路径和 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // O(n)空间复杂度
        int[] dp = new int[n];
        dp[0] = triangle.getFirst().getFirst();
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    dp[j] = dp[j - 1] + triangle.get(i).get(j);
                } else if (j == 0) {
                    dp[j] = dp[j] + triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int num : dp) {
            ans = Math.min(ans, num);
        }
        return ans;
    }
}
