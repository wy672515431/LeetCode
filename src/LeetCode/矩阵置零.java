package LeetCode;

import java.util.Arrays;

public class 矩阵置零 {
    public void setZeroes(int[][] matrix) {
        int flag1 = 0, flag2 = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                flag1 = 1;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flag2 = 1;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (flag1 == 1) {
            Arrays.fill(matrix[0], 0);
        }

        if (flag2 == 1) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
