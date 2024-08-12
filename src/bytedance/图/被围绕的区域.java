package bytedance.图;

public class 被围绕的区域 {
    int n, m;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void solve(char[][] board) {
        n = board.length;
        m = board[0].length;
        // 我们可以从边缘的O开始DFS，将所有与边缘O相连的O都标记为A
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < m; j++) {
                // 将所有O变为X，将所有A变为O
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        for (int i = 0; i < 4; i++) {
            dfs(board, x + dirs[i][0], y + dirs[i][1]);
        }
    }
}
