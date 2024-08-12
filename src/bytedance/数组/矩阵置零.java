package bytedance.数组;

import java.util.Arrays;

public class 矩阵置零 {
    public void setZeroes(int[][] matrix) {
        int flag1 = 0, flag2 = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        // 用第一行和第一列来记录是否有0
        // 第一行是否有0
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == 0) {
                flag1 = 1;
                break;
            }
        }

        // 第一列是否有0
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                flag2 = 1;
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    // 代表第i行和第j列需要置0
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (flag1 == 1) {
            Arrays.fill(matrix[0], 0);
        }

        if (flag2 == 1) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
