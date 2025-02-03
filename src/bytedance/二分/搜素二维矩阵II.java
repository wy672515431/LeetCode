package bytedance.二分;

public class 搜素二维矩阵II {
    public boolean searchMatrix(int[][] matrix, int target) {
        int end = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            end = lowerBound(matrix[i], end, target);
            if (matrix[i][end] == target) {
                return true;
            } else {
                if (matrix[i][end] > target) {
                    end = end - 1;
                }
            }
        }
        return false;
    }

    private int lowerBound(int[] row, int end, int target) {
        int start = 0;
        int mid;
        while (start < end) {
            mid = (end - start) / 2 + start;
            if (row[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 从矩阵的右上角搜索
     * 对于(x,y)
     * 如果matrix[x][y] == target true
     * matrix[x][y] > target，说明y列全不符合, y--
     * matrix[x][y] < target  说明x行不符合 x++
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean zSearch(int[][] matrix, int target) {
        int x = 0;
        int y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] == target)
                return true;
            else if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }
}
