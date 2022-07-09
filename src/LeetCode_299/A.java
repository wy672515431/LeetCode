package LeetCode_299;

public class A {
    public boolean checkXMatrix(int[][] grid) {
        int m = grid.length;
        //
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                //判断是否是矩阵对角线的元素
                //i == j || i + j == m
                if (i == j || i + j == m - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
