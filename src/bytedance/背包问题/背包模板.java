package bytedance.背包问题;

public class 背包模板 {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;

        // 二维数组 dp[i][j]表示从0 ~ i类物品中选取种类不超过j物品的最大价值
        // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
        int[][] dp = new int[weight.length][bagWeight + 1];
        // 初始化
        for (int j = bagWeight; j >= weight[0]; j--) {
            dp[0][j] = dp[0][j - weight[0]] + value[0];
        }

        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                // 装不下
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

//        System.out.println(dp[weight.length - 1][bagWeight]);

        // 如果是恰好则，需要将数组初始化为inf
        // 我们发现dp过程中，状态转移与前面的状态有关，可以从后向前遍历，降维
        int[] dp1 = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp1[j] = Math.max(dp1[j], dp1[j - weight[i]] + value[i]);
            }
        }
//        System.out.println(dp1[bagWeight]);

        // 完全背包，则为从前向后遍历
    }
}
