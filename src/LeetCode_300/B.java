package LeetCode_300;

import java.util.HashMap;

public class B {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        int left = 0;
        int top = 0;
        int right = n - 1;
        int bottom = m - 1;
        int x = 0;
        int y = 0;
        while (left <= right && top <= bottom) {
            //左上角
            if (x == left && y == top) {
                while (x < right) {
                    matrix[y][x] = (head == null) ? -1 : head.val;
                    head = (head == null) ? head : head.next;
                    x++;
                }
                // x == right
                matrix[y][x] = (head == null) ? -1 : head.val;
                head = (head == null) ? head : head.next;
                top++;
                y++;
                //右上角
            } else if (x == right && y == top) {
                while (y < bottom) {
                    matrix[y][x] = (head == null) ? -1 : head.val;
                    head = (head == null)? head : head.next;
                    y++;
                }
                matrix[y][x] = (head == null) ? -1 : head.val;
                head = (head == null) ? head : head.next;
                right--;
                x--;
            } else if (x == right && y == bottom) {
                while (x > left) {
                    matrix[y][x] = (head == null) ? -1 : head.val;
                    head = (head == null) ? head : head.next;
                    x--;
                }
                matrix[y][x] = (head == null) ? -1 : head.val;
                head = (head == null) ? head : head.next;
                bottom--;
                y--;
            } else if (x == left && y == bottom) {
                while (y > top) {
                    matrix[y][x] = (head == null) ? -1 : head.val;
                    head = (head == null) ? head : head.next;
                    y--;
                }
                matrix[y][x] = (head == null) ? -1 : head.val;
                head = (head == null) ? head : head.next;
                left++;
                x++;
            }
        }
        return matrix;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
