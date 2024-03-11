package LeetCode;

import 数据结构学习计划.扁平化多级双向链表;

import java.util.Arrays;

public class 黑白翻转棋 {
    public static void main(String[] args) {
        String[] chessboard = {"......","......","XOOO..","..OOOX","...XX.","......","......"};
        黑白翻转棋 a = new 黑白翻转棋();
        a.flipChess(chessboard);
    }

    int cnt = 0;
    int cnt1 = 0;

    /**
     * 'X' for black chess, 'O' for white chess, '.' for empty space
     *
     * @param chessboard
     * @return
     */
    public int flipChess(String[] chessboard) {
        int ans = 0;
        int n = chessboard.length;
        int m = chessboard[0].length();
        char[][] matrix = Arrays.stream(chessboard).sequential()
                .map(String::toCharArray).toArray(char[][]::new);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = matrix[i][j];
                if (ch == '.') {
                    cnt = 0;
                    char[][] copyMatrix = Arrays.stream(matrix)
                            .map(arr -> Arrays.copyOf(arr, arr.length))
                            .toArray(char[][]::new);
                    copyMatrix[i][j] = 'X';
                    solve(copyMatrix);
                    ans = Math.max(ans, cnt);
                }
            }
        }
        return ans;
    }

    // 纵向、横向、主对角线、斜对角线
    public void solve(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean update = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'O') {
                    cnt1 = 0;
                    check(matrix, i, j);
                    if (cnt1 != 0) {
                        update = true;
                        cnt += cnt1;
                    }
                }
            }
        }
        if (update) {
            solve(matrix);
        }
    }

    public void check(char[][] matrix, int i, int j) {
        int n = matrix.length;
        int m = matrix[0].length;
        int up = -1;
        int down = -1;
        boolean update = false;
        // 横向, 最近的黑棋且中间没有.
        for (int k = 0; k < j; k++) {
            char ch = matrix[i][k];
            if (ch == 'X') {
                up = k;
            } else if (ch == '.') {
                up = -1;
            }
        }
        for (int k = m - 1; k > j; k--) {
            char ch = matrix[i][k];
            if (ch == 'X') {
                down = k;
            } else if (ch == '.') {
                down = -1;
            }
        }
        if (up != -1 && down != -1) {
            cnt1 += j - up - 1;
            cnt1 += down - j - 1;
            update = true;
            for (int k = up; k <= down; k++) {
                matrix[i][k] = 'X';
            }
        }
        // 纵向
        up = -1;
        down = -1;
        for (int k = 0; k < i; k++) {
            char ch = matrix[k][j];
            if (ch == 'X') {
                up = k;
            } else if (ch == '.') {
                up = -1;
            }
        }
        for (int k = n - 1; k > i; k--) {
            char ch = matrix[k][j];
            if (ch == 'X') {
                down = k;
            } else {
                down = -1;
            }
        }
        if (up != -1 && down != -1) {
            cnt1 += i - up - 1;
            cnt1 += down - i - 1;
            update = true;
            for (int k = up; k <= down; k++) {
                matrix[k][j] = 'X';
            }
        }
        // 对角线
        up = -1;
        down = -1;

        for (int x = i - 1, y = j - 1; x >= 0 && y >= 0; x--, y--) {
            char ch = matrix[x][y];
            if (ch == '.') {
                up = -1;
                break;
            } else if (ch == 'X') {
                up = x;
                break;
            }
        }
        for (int x = i + 1, y = j + 1; x < n && y < m; x++, y++) {
            char ch = matrix[x][y];
            if (ch == '.') {
                down = -1;
                break;
            } else if (ch == 'X') {
                down = x;
                break;
            }
        }
        if (up != -1 && down != -1) {
            cnt1 += i - up - 1;
            cnt1 += down - i - 1;
            update = true;
            for (int k = up; k <= down; k++) {
                matrix[k][j - (i - k)] = 'X';
            }
        }

        up = -1;
        down = -1;

        for (int x = i - 1, y = j + 1; x >= 0 && y < m; x--, y++) {
            char ch = matrix[x][y];
            if (ch == '.') {
                up = -1;
                break;
            } else if (ch == 'X') {
                up = x;
                break;
            }
        }

        for (int x = i + 1, y = j - 1; x < n && y >= 0; x++, y--) {
            char ch = matrix[x][y];
            if (ch == '.') {
                down = -1;
                break;
            } else if (ch == 'X') {
                down = x;
                break;
            }
        }

        if (up != -1 && down != -1) {
            cnt1 += i - up - 1;
            cnt1 += down - i - 1;
            update = true;
            for (int k = up; k <= down; k++) {
                matrix[k][j + (i - k)] = 'X';
            }
        }
        if (update) {
            cnt1 += 1;
            matrix[i][j] = 'X';
        }
    }
}
