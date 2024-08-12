package bytedance.数组;

public class 生命游戏 {
    // 8个方向
    int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    int n;
    int m;
    public void gameOfLife(int[][] board) {
        n = board.length;
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cell = board[i][j];
                if (cell == 1) {
                    updateLiveCell(board, i, j);
                } else {
                    updateDeadCell(board, i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }

    /**
     * board[x][y] == 2 代表之前是活的，但现在是死的
     * board[x][y] == 3 代表之前是死的，但现在是活的
     * @param board
     * @param x
     * @param y
     */
    private void updateLiveCell(int[][] board, int x, int y) {
        int liveCount = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && (board[nx][ny] == 1 || board[nx][ny] == 2)) {
                liveCount++;
            }
        }
        if (liveCount < 2 || liveCount > 3) {
            board[x][y] = 2;
        }
    }

    private void updateDeadCell(int[][] board, int x, int y) {
        int liveCount = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && (board[nx][ny] == 1 || board[nx][ny] == 2)) {
                liveCount++;
            }
        }
        if (liveCount == 3) {
            board[x][y] = 3;
        }
    }
}
