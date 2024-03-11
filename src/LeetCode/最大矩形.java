package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 等价于柱状图最大矩形
 */
public class 最大矩形 {
    public int maximalRectangle(char[][] matrix) {
        // 枚举matrix[i][j]为右下角的矩形
        // left[i][j]表示matrix[i][j]为起点左边重复一的数量
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] left = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char ch = matrix[i][j];
                if (ch == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ans = 0;
        // 针对每一列
        for (int j = 0; j < col; j++) {
            int[] up = new int[row];
            int[] down = new int[row];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = row - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < row; i++) {
                int height = down[i] - up[i] - 1;
                ans = Math.max(ans, height * left[i][j]);
            }
        }
        return ans;
    }
}
