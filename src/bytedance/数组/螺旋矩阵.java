package bytedance.数组;

import java.util.ArrayList;
import java.util.List;

public class 螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            // 从上到下
            for (int i = top + 1; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            // 从右到左
            // 从下到上，可能出现left == right 和 top == bottom的情况, 这时候如果在进行下面的操作，会出现重复的情况
            if (left < right && top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                for (int i = bottom - 1; i >= top + 1; i--) {
                    ans.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }
}
