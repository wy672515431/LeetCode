package bytedance.递归;

public class 单词搜索 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean ans = false;
    int n;
    boolean[][] isVisited;
    public boolean exist(char[][] board, String word) {
        n = word.length();
        isVisited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                solve(board, word, i, j, 0);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }

    private void solve(char[][] borad, String word, int x, int y, int cur) {
        // 已经成功了，不需要在进行下去
        if (ans) {
            return;
        }
        char ch = word.charAt(cur);
        if (ch != borad[x][y]) {
            return;
        }
        if (cur == n - 1) {
            ans = true;
            return;
        }
        // 如果相同，进行下一步计算
        isVisited[x][y] = true;
        for (int i = 0; i < dirs.length; i++) {
            int x1 = x + dirs[i][0];
            int y1 = y + dirs[i][1];
            if (x1 >= 0 && x1 < borad.length && y1 >= 0 && y1 < borad[0].length && !isVisited[x1][y1]) {
                solve(borad, word, x1, y1, cur + 1);
            }
        }
        isVisited[x][y] = false;
    }

}
