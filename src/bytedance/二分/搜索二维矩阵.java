package bytedance.二分;

public class 搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int high = m * n - 1, low = 0;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = mid / n;
            int y = mid % n;
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
