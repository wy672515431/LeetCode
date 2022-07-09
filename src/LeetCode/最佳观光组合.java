package LeetCode;

/**
 * 给你一个正整数数组 values，其中 values[i]表示第 i 个观光景点的评分，并且两个景点i 和j之间的 距离 为j - i。
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 *
 * dp[i]代表前i个中的最大得分，引入一个新的地点,只需考虑前面的值和新值即可。
 * 每增加一个新值,i - j 则会增大，我们等价的作用到value上面
 */
public class 最佳观光组合 {
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = values[1] + values[0] - 1;
        int maxValue = Math.max(values[0] - 2, values[1] - 1);
        for (int i = 2; i < values.length; i++) {
            maxScore = Math.max(maxValue + values[i], maxScore);
            maxValue = Math.max(maxValue, values[i]);
            maxValue--;
        }
        return maxScore;
    }
}
