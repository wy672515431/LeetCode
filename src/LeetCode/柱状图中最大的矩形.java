package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class 柱状图中最大的矩形 {
    public static int largestRectangleArea(int[] heights) {
        int ans = 0;
        Stack<Integer> height = new Stack<>();
        Stack<Integer> pos = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!height.isEmpty() && height.peek() >= heights[i]) {
                // calc len
                int recHeight = height.pop();
                int recPos = pos.pop();
                int leftLen = height.isEmpty() ? recPos + 1 : recPos - pos.peek();
                int rightLen = i - recPos - 1;
                ans = Math.max(ans, recHeight * (leftLen + rightLen));
            }
            height.push(heights[i]);
            pos.push(i);
        }

        int right = heights.length - 1;
        while (!height.isEmpty()) {
            int recHeight = height.pop();
            int recPos = pos.pop();
            int leftLen = height.isEmpty() ? recPos + 1 : recPos - pos.peek();
            int rightLen = right - recPos;
            ans = Math.max(ans, recHeight * (leftLen + rightLen));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {2, 3};
        System.out.println(largestRectangleArea(heights));
    }

    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
