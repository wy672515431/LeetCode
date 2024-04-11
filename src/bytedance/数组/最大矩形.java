package bytedance.数组;

import java.util.Stack;

public class 最大矩形 {
    // 类似于求柱状图
    public int maximalRectangle(char[][] matrix) {
        // 首先统计出高, 即从左到右连续一的个数
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] height = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = matrix[i][j];
                if (j == 0) {
                    height[i][j] = ch == '0' ? 0 : 1;
                } else {
                    height[i][j] = ch == '0' ? 0 : height[i][j - 1] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            // 固定高，扫描宽，找到柱子左右两边最近小于它高度的柱子
            int[] up = new int[n];
            int[] down = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < n; j++) {
                while (!stack.isEmpty() && height[j][i] <= height[stack.peek()][i]) {
                    stack.pop();
                }
                up[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            stack.clear();
            for (int j = n - 1; j >= 0; j--) {
                while (!stack.isEmpty() && height[j][i] <= height[stack.peek()][i]) {
                    stack.pop();
                }
                down[j] = stack.isEmpty() ? n : stack.peek();
                stack.push(j);
            }
            for (int j = 0; j < n; j++) {
                int width = down[j] - up[j] - 1;
                ans = Math.max(ans, width * height[j][i]);
            }
        }
        return ans;
    }
}
