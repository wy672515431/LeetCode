package LeetCode_299;

public class B {
    static final int mod = 1000000007;
    public int countHousePlacements(int n) {
        //两边，每边各有n个土块,同一侧不能存在两所房子相邻的情况.
        //两边没有房子数目要求
        //dp[i][0] 代表第i块土地，不建房子的情况
        //dp[i][1] 代表第i块土地，建房子的情况
        int[][] dp = new int[n][2];
        dp[0][1] = 1;
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][0]) % mod;
            dp[i][1] = dp[i - 1][0] % mod;
        }
        long oneWaySum = (dp[n - 1][1] + dp[n - 1][0]) % mod;
        return (int)(((oneWaySum % mod) * (oneWaySum % mod)) % mod);
    }
}
