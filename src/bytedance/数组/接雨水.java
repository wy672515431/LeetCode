package bytedance.数组;

import java.util.Arrays;
import java.util.Stack;

public class 接雨水 {
    // 对于每一个位置，本质上是要找到左右两侧，最高于其高度的位置
    public static int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int leftMax = height[0];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            left[i] = leftMax;
        }
        int rightMax = height[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            }
            ans += Math.min(left[i], rightMax) - height[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        trap(height);
    }
}
