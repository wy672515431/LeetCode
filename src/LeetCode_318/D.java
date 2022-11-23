package LeetCode_318;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class D {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        robot.sort(Comparator.comparingInt(o -> o));
        Arrays.sort(factory, Comparator.comparingInt(o -> o[0]));
        int robotNum = robot.size();
        int factoryNum = factory.length;
        long[][] dp = new long[factoryNum + 1][robotNum + 1];
        // n个工厂修理m个机器人
        // dp[i][j]前i个工厂修理前j个机器人，移动的最小距离
        // dp[i][j] 一个都不修 dp[i - 1][j]
        // dp[i][j] = dp[i - 1][j - k] + cost(i, j, k)
        // k <= min(j, factory[i][1])
        for (long[] longs : dp) {
            Arrays.fill(longs, (long)(1e15));
        }
        long cost = 0L;
        // 一个也不修的代价是0
        for (int i = 1; i <= factoryNum; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= Math.min(robotNum, factory[0][1]); i++) {
            cost += Math.abs(robot.get(i - 1) - factory[0][0]);
            dp[1][i] = cost;
        }
        for (int i = 2; i <= factoryNum; i++) {
            for (int j = 1; j <= robotNum; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                // 本工厂修的个数
                cost = 0L;
                for (int k = 1; k <= Math.min(j, factory[i - 1][1]); k++) {
                    cost += Math.abs(robot.get(j - k) - factory[i - 1][0]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + cost);
                }
            }
        }
        return dp[factoryNum][robotNum];
    }
}
