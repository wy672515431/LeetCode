package LeetCode_82_Double;

import java.util.Stack;

public class D {
    /**
     * 这道题本质上是看子数组中的最小值大于threshold / k
     * 我们不妨假设数组的每个元素为子数组的最小值
     * 找到其左边界和右边界即可
     * @param nums
     * @param threshold
     * @return
     */
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        //left[i]表示左边小于nums[i]的最近下标
        int[] left = new int[n];
        //right[i]表示右边小于nums[i]的最近下标
        int[] right = new int[n];
        //单调栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            left[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty()) ? n : stack.peek();
            stack.push(i);
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int k = right[i] - left[i] - 1;
            if (nums[i] > threshold / k) {
                ans = k;
            }
        }
        return ans;
    }
}
