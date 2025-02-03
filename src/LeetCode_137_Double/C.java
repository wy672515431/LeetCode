package LeetCode_137_Double;

import java.util.PriorityQueue;

public class C {
    int m, n;
    boolean[] isVisited;
    long ans = Long.MIN_VALUE;
    long sum = 0L;
    int[][] topThreeNums;
    public long maximumValueSum(int[][] board) {
        m = board.length;
        n = board[0].length;
        // 表示该列是否存在车
        // 我们统计每一行的前三大的数
        topThreeNums = new int[m][3];
        for (int i = 0; i < m; i++) {
            final int row = i;
            PriorityQueue<Integer> pq = new PriorityQueue<>(3, (o1, o2) -> board[row][o2] - board[row][o1]);
            for (int j = 0; j < n; j++) {
                pq.offer(j);
            }
            for (int j = 0; j < 3; j++) {
                topThreeNums[i][j] = pq.poll();
            }
        }
        isVisited = new boolean[n];
        solve(board, 0, 0);
        return ans;
    }

    /**
     *
     * @param board 棋盘
     * @param cur 当前的行号
     * @param k  当前的车的数量
     */
    private void solve(int[][] board, int cur, int k) {
        if (k == 3) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = cur; i < m; i++) {
            // 选择前三大的
            for (int j = 0; j < 3; j++) {
                int col = topThreeNums[i][j];
                if (isVisited[col]) {
                    continue;
                }
                isVisited[col] = true;
                sum += board[i][col];
                solve(board, i + 1, k + 1);
                sum -= board[i][col];
                isVisited[col] = false;
            }
        }
    }
}
