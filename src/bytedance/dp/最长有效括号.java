package bytedance.dp;

import java.util.Stack;

public class 最长有效括号 {
    public int longestValidParentheses(String s) {
        int len = s.length();
        // 以i结尾的子串长度
        int[] dp = new int[len + 1];
        int ans = 0;
        Stack<Character> parens = new Stack<>();
        // 存放对应括号的位置
        Stack<Integer> positions = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                // 进栈
                parens.push(ch);
                positions.push(i);
            } else {
                if (!parens.isEmpty() && parens.peek() == '(') {
                    // 匹配
                    dp[i + 1] = dp[i];
                    int position = positions.peek();
                    //  加上左括号之前的部分
                    dp[i + 1] += dp[position] + 2;
                    ans = Math.max(ans, dp[i + 1]);
                    positions.pop();
                    parens.pop();
                } else {
                    parens.push(ch);
                    positions.push(i);
                }
            }
        }
        return ans;
    }
}
