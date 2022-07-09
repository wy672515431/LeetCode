package LeetCode_81_Double;

import java.util.ArrayList;

public class D {
    static final int mod = 1000000007;

    public int distinctSequences(int n) {
        //dp[i][j]表示序列长度为i且以结尾的序列数
        int[][] dp = new int[n][7];
        int[][] sum = new int[2][7];
        //1的公约数为1的有1,2,3,4,5,6
        //2 -> 1,3,5
        //3 -> 1,2,4,5
        //4 -> 1,3,5
        //5 -> 1,2,3,4,6
        //6 -> 1,5
        for (int i = 1; i <= 6; i++) {
            dp[0][i] = 1;
            //偶数项的和
            sum[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    //保证i - 1与i的最大公约数为1
                    //也要保证i != i - 1且 i != i - 2 且 i - 1 != i - 2
                    //我们要减去 i == i - 1 或者 i == i - 2的个数
                    if (j != k && gcd(j, k) == 1){
                        if ((i & 1) == 1) {
                            dp[i][j] = (dp[i][j] + sum[0][k]) % mod;
                            dp[i][j] = (dp[i][j] - sum[1][j] + mod) % mod; 
                        } else {
                            dp[i][j] = (dp[i][j] + sum[1][k]) % mod;
                            dp[i][j] = (dp[i][j] - sum[0][j] + mod) % mod; 
                        }
                        // for (int l = 1; l <= i; l++) {
                        //     if ((l & 1) == 1) {
                        //         dp[i][j] = (dp[i][j] + dp[i - l][k]) % mod;
                        //     } else {
                        //         dp[i][j] = (dp[i][j] - dp[i - l][j] + mod) % mod;
                        //     }
                        // }
                    }
                }
                if ((i & 1) == 1)
                    sum[1][j] = (sum[1][j] + dp[i][j]) % mod;
                else 
                    sum[0][j] = (sum[0][j] + dp[i][j]) % mod;
            }   
        }
        int ans = 0;
        for (int i = 1; i <= 6; i++) {
            ans = (ans + dp[n - 1][i]) % mod;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);  
    }

    public static void main(String[] args) {
        System.out.println(new D().distinctSequences(10));
    }
}
