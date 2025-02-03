package bytedance.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 蛇梯棋 {
    public int snakesAndLadders(int[][] board) {
        List<List<Integer>> edge = new ArrayList<>();
        int n = board.length;
        for (int i = 0; i <= n * n; i++) {
            edge.add(new ArrayList<>());
        }
        boolean leftToRight = false;
        for (int i = n - 1; i >= 0; i--) {
            leftToRight = !leftToRight;
            // j + 1 和 n - j
            for (int j = 0; j < n; j++) {
                int id = (n - 1 - i) * n + (leftToRight ? j + 1 : n - j);
                for (int k = id + 1; k <= Math.min(id + 6, n * n); k++) {
                    // 获得其对应的行和列
                    int row = (n - 1) - (k - 1) / n;
                    int col = (k - 1) % n;
                    boolean temp = leftToRight;
                    if ((i - row) % 2 != 0) {
                        temp = !temp;
                    }
                    if (!temp) {
                        col = n - 1 - col;
                    }
                    if (board[row][col] == -1) {
                        edge.get(id).add(k);
                    } else {
                        edge.get(id).add(board[row][col]);
                    }
                }
            }
        }
        int ans = -1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (visited[cur]) {
                    continue;
                }
                visited[cur] = true;
                if (cur == n * n) {
                    return ans;
                }
                for (int next : edge.get(cur)) {
                    if (!visited[next]) {
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }
}
