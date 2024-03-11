package LeetCode;

public class RotateBox {
    /**
     * '#'代表石头,'*'代表障碍物,'.'代表空位置
     *
     * @param box 我们需要将box顺时针旋转90°，受到重力的影响，石头会往下掉落
     *            直到掉到另一块障碍物，底部或石头上
     * @return the rotated box
     */
    public static char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        char[][] ans = new char[m][n];
        // 瞬时针旋转90度.
        // (1, 0) -> (0, 0)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (box[j][i] == '*') {
                    ans[i][n - 1 - j] = box[j][i];
                } else {
                    ans[i][n - 1 - j] = '.';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                char ch = box[i][j];
                if (ch == '#') {
                    count++;
                } else if (ch == '*') {
                    if (count != 0) {
                        ans[j - 1][n - 1 - i] = '#';
                        ans[j - count][n - 1 - i] = '#';
                    }
                    count = 0;
                }
            }
            // bottom
            if (count != 0) {
                ans[m - 1][n - 1 - i] = '#';
                ans[m - count][n - 1 - i] = '#';
            }
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < m; j++) {
                char ch = ans[j][i];
                if (ch == '#') {
                    flag = !flag;
                } else if (ch == '.' && flag) {
                    ans[j][i] = '#';
                } else if (ch == '*') {
                    flag = false;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] box = {{'#', '#', '*', '.', '*', '.'}, {'#', '#', '#', '*', '.', '.'}
                , {'#', '#', '#', '.', '#', '.'}};
        rotateTheBox(box);
    }
}
