package LeetCode;

/**
 * 卡特兰数
 * 对于一个有序序列[1,n]
 * 我们可以针对每一个i，构造二叉搜索数.左侧的节点是[0 ~ i - 1]，右侧的节点是[i + 1 , n];  G(i) * G(n - i)
 */
public class 不同的二叉搜索树 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0 ; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }   
}
