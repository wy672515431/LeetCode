package LeetCode;

import java.util.Stack;

public class 最长有效括号 {
    /**
     *
     * @param s 包含(和)的字符串
     * @return 找出格式正确且连续的括号最长子串的长度
     */
    public int longestValidParentheses(String s) {
        int length = s.length();
        int[] dp = new int[length + 1];
        int max = 0;
        Stack<Character> stack = new Stack<>();
        Stack<Integer> position = new Stack<>();

        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
                position.push(i + 1);
                dp[i + 1] = 0;
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    dp[i + 1] = dp[i];
                    int pos = position.pop();
                    dp[i + 1] += dp[pos - 1] + 2;
                    max = Math.max(dp[i + 1], max);
                    stack.pop();
                } else {
                    stack.push(ch);
                    position.push(i);
                }
            }
        }
        return max;
    }
}
