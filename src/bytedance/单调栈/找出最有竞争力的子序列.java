package bytedance.单调栈;

import java.util.Stack;

public class 找出最有竞争力的子序列 {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 我们将某个数字出栈时，必须保证[i + 1, n - 1]的个数大于k - ans.size() - 1
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i] && (n - i) > k - stack.size()) {
                stack.pop();
            }
            // 入栈必须保证stack中的数字个数小于k
            if (stack.size() < k) {
                stack.push(i);
            }
        }
        return stack.stream().mapToInt(i -> nums[i]).toArray();
    }
}
