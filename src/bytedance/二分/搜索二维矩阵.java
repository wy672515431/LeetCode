package bytedance.二分;

public class 搜索二维矩阵 {
    /**
     * 或者我们可以进行两次二分查找，首先在第一列上进行二分查找，查找到第一个大于等于 target 的行为 line， 然后在line - 1上进行二分查找。
     *
     * @param matrix
     * @param target
     * @return
     */
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
