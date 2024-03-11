package bytedance.dp;

import java.util.Arrays;
import java.util.Scanner;

public class 古生物血缘远近判定 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawInput = scanner.next();
        String[] DNAs = rawInput.split(",");
        String DNA1 = DNAs[0];
        String DNA2 = DNAs[1];
        int ans = solve(DNA1, DNA2);
        System.out.println(ans);
    }

    // dp[i][j] 表示DNA的前i个字符和DNA2的前j个字符最小变异情况算法
    public static int solve(String DNA1, String DNA2) {
        int len1 = DNA1.length();
        int len2 = DNA2.length();
        // DNA序列可能为空
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        int[][] dp = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < len1; i++) {
            char ch1 = DNA1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char ch2 = DNA2.charAt(j);
                if (i == 0 && j == 0) {
                    if (ch1 == ch2) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1;
                    }
                } else if (i == 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                } else if (j == 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                } else {
                    // 直接匹配
                    if (ch1 == ch2) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                    }
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
