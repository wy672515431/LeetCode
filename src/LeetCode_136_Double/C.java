package LeetCode_136_Double;

public class C {
    public int minFlips(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        // 行和列都为回文
        // (i, j) -> (i, m - j - 1)
        // (i, j) -> (n - i - 1, j)
        // (n - i - 1, j) -> (n - i - 1, m - j - 1)
        int ans = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                int n1 = grid[i][j];
                int n2 = grid[i][m - j - 1];
                int n3 = grid[n - i - 1][j];
                int n4 = grid[n - i - 1][m - j - 1];
                // 统计 0 和 1 的个数
                int[] cnt = new int[2];
                cnt[n1]++;
                cnt[n2]++;
                cnt[n3]++;
                cnt[n4]++;
                ans += Math.min(cnt[0], cnt[1]);
            }
        }
        int cnt1 = 0;
        int times = 0;
        int times1 = 0;
        if (n % 2 == 1) {
            for (int j = 0; j < m / 2; j++) {
                int n1 = grid[n / 2][j];
                int n2 = grid[n / 2][m - j - 1];
                if (n1 == 1 && n2 == 1) {
                    cnt1 += 2;
                } else if (n1 == 0 && n2 == 0) {
                    times1++;
                } else {
                    times++;
                }
            }
        }
        if (m % 2 == 1) {
            for (int i = 0; i < n / 2; i++) {
                int n1 = grid[i][m / 2];
                int n2 = grid[n - i - 1][m / 2];
                if (n1 == 1 && n2 == 1) {
                    cnt1 += 2;
                } else if (n1 == 0 && n2 == 0) {
                    times1++;
                } else {
                    times++;
                }
            }
        }
        if (n % 2 == 1 && m % 2 == 1) {
            int n1 = grid[n / 2][m / 2];
            if (n1 == 1) {
                cnt1++;
            }
        }
        if (cnt1 % 4 == 0) {
            return ans + times;
        } else if (cnt1 % 4 == 1) {
            // 将中间的1翻转即可
            return ans + times + 1;
        } else if (cnt1 % 4 == 2) {
            // 如果存在1 和 0，则只用翻转1次，否则要翻转两个0
            if (times != 0) {
                return ans + times;
            } else {
                return ans + times + 2;
            }
        } else {
            //  存在中间1 和 多余两个1
            ans += 1;
            if (times != 0) {
                ans += times;
            } else {
                ans += times + 2;
            }
            return ans;
        }
    }
}
