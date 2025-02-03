package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class N皇后 {
    boolean[][] isVisited;
    int[] column;
    List<List<String>> ans = new ArrayList<>();
    int count = 0;
    public List<List<String>> solveNQueens(int n) {
        // isVisited[0][i] 代表i列是否存在皇后
        // isVisited[1][i + cur] 代表左下到右上的斜边
        // isVisited[2][i - cur + n] 代表左上到右下的斜边
        isVisited = new boolean[3][40];
        column = new int[n];
        solve(n, 0);
        return ans;
    }

    /**
     *
     * @param n
     * @param cur 当前的行号
     */
    private void solve(int n, int cur) {
        if (cur == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (column[i] == j) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                temp.add(sb.toString());
            }
            ans.add(temp);
            count++;
            return;
        }
        // i - column
        for (int i = 0; i < n; i++) {
            // 列、左下到右上斜线、左上到右下斜线
            if (isVisited[0][i] || isVisited[1][i + cur] || isVisited[2][i - cur + n]) {
                continue;
            }
            isVisited[0][i] = isVisited[1][i + cur] = isVisited[2][i - cur + n] = true;
            column[cur] = i;
            solve(n, cur + 1);
            isVisited[0][i] = isVisited[1][i + cur] = isVisited[2][i - cur + n] = false;
        }
    }
}
