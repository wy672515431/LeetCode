package LeetCode;

/**
 * 给定一个 nn 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 */
public class _48 {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for(int j = 0; j < length / 2; j++) {
            for(int i = j; i < length - 1 - j; i++) {
                int tem = matrix[j][i];
                int tem1 = matrix[i][length - 1 - j];
                int tem2 = matrix[length - 1 - j][length - i - 1];
                int tem3 = matrix[length - i - 1][j];
                matrix[j][i] = tem3;
                matrix[i][length - 1 - j] = tem;
                matrix[length - 1 - j][length - i -1] = tem1;
                matrix[length - i - 1][j] = tem2;
            }
        }
    }
}
