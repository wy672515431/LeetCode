package bytedance.dp.状态压缩dp;

public class 覆盖 {
    public static void main(String[] args) {
        覆盖 main = new 覆盖();
        System.out.println(main.domino(3 , 2, new int[][]{{1, 1}, {2, 1}}));
    }
    public int domino(int n, int m, int[][] broken) {
        // dp[i][state], i表示当前行，state表示当前行的状态 i ~ n - 1能够放置的最大数量
        // 在第i行时，我们优先竖着放置，然后横着放置
        // 从底向上遍历
        int[][] dp = new int[n + 1][1 << m];
        int[] blocked = new int[n + 1];
        // 最后一行全是障碍
        blocked[n] = (1 << m) - 1;
        for (int[] block : broken) {
            blocked[block[0]] |= 1 << block[1];
        }
        for (int i = n - 1; i >= 0; i--) {
            // st代表第i行被占用的位置，将broken格子的位置排除
            for (int st = (~blocked[i] & ((1 << m) - 1)); ; st = (st - 1) & (~blocked[i] & ((1 << m) - 1))) {
                int maxCnt = 0;
                // 竖着放置, 考虑当前行和下一行
                int st1 = st & (~blocked[i + 1]);
                // 枚举竖着放的集合
                for (int sub = st1; ; sub = (sub - 1) & st1) {
                    maxCnt = Math.max(maxCnt, Integer.bitCount(sub) + horizontalPlacement(st & (~sub))
                            + dp[i + 1][blocked[i + 1] | sub]);
                    if (sub == 0) {
                        break;
                    }
                }
                dp[i][(~st) & ((1 << m) - 1)] = maxCnt;
                if (st == 0) {
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 1 << m; i++) {
            ans = Math.max(ans, dp[0][i]);
        }
        return ans;
    }

    /**
     * 在当前状态下，横向放置能够放置的最大数量
     * @param state 当前状态
     * @return 横向放置能够放置的最大数量
     */
    private int horizontalPlacement(int state) {
        int ans = 0;
        while (state > 0) {
            int lastOne = state & -state;
            if ((state & (lastOne << 1)) != 0) {
                ans++;
            }
            state &= ~lastOne;
            state &= ~(lastOne << 1);
        }
        return ans;
    }
}
