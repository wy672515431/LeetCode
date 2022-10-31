package LeetCode;

import java.util.Stack;

public class 括号的分数 {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        //深度为0
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //如果遇到的是左括号，我们需要计算左括号内部的子平衡字符串A的分数，我们也需要压入分数0，占位
            //表示A前面空字符串的分数
            if (ch == '(') {
                stack.push(0);
                //如果遇到右括号，那么代表内部子平衡字符串A的分数已经求出来了，我们将他弹出栈，并保存到变量v中
                //如果v=0，则说明A是空串，分数为1，若不为0，则为二倍。然后将分数加到栈顶元素上面。
            } else if (ch == ')') {
                int tem1 = stack.pop();
                int tem2 = stack.pop();
                tem2 += Math.max(tem1 * 2, 1);
                stack.push(tem2);
            }
        }
        return stack.pop();
    }
}
