package test;

import java.util.HashMap;

public class dialog {
    public static void main(String[] args) {

    }

    public int[] findDiagonalOrder(int[][] mat) {
        int[] ans = new int[mat.length * mat[0].length];
        int r = 0, c = 0;
        //遍历起始点
        for (int i = 0; i < ans.length; i++) {
            ans[i] = mat[r][c];
            //向上遍历,横坐标和纵坐标之间的奇偶性
            if ((r + c) % 2 == 0) {
                if (c == mat[0].length - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    c++;
                    r--;
                }
            } else {
                if (r == mat.length - 1)
                    c++;
                else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }

            }
        }
        return ans;
    }
}




