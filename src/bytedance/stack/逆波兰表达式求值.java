package bytedance.stack;

import java.util.Stack;

public class 逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+": {
                    int opr = stack.pop();
                    int opl = stack.pop();
                    stack.push(opl + opr);
                    break;
                }
                case "-": {
                    int opr = stack.pop();
                    int opl = stack.pop();
                    stack.push(opl - opr);
                    break;
                }
                case "*": {
                    int opr = stack.pop();
                    int opl = stack.pop();
                    stack.push(opl * opr);
                    break;
                }
                case "/": {
                    int opr = stack.pop();
                    int opl = stack.pop();
                    stack.push(opl / opr);
                    break;
                }
                default: {
                    stack.push(Integer.parseInt(token));
                }
            }
        }
        return stack.pop();
    }
}
