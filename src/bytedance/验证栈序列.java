package bytedance;

import java.util.Stack;

public class 验证栈序列 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = popped.length;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int j = 0; j < n; j++) {
            while (stack.isEmpty() || stack.peek() != popped[j]) {
                if (i == n) {
                    return false;
                }
                stack.push(pushed[i++]);
            }
            if (stack.peek() == popped[j]) {
                stack.pop();
            }
            // 此时，pushed[i] = popped[j]
        }
        return true;
    }
}
