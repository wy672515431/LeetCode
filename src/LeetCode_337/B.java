package LeetCode_337;

public class B {
    boolean[][] isVisited;
    int n, m;
    int cnt = 0;
    int[][] grid;
    int[][] directions = {{2, 1}, {1, 2}, {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}, {2, -1}, {1, -2}};
    public boolean checkValidGrid(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        isVisited = new boolean[n][m];
        this.grid = grid;
        checkValidMove(0, 0);
        return isAllVisited();
    }

    public boolean isAllVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isVisited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkValidMove(int row, int col) {
        isVisited[row][col] = true;
        cnt++;
        for (int[] direction : directions) {
            int x = direction[0];
            int y = direction[1];
            int newRow = row + x;
            int newCol = col + y;
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
                    && !isVisited[newRow][newCol] && grid[newRow][newCol] == cnt) {
                checkValidMove(newRow, newCol);
            }
        }
        cnt--;
    }
}
