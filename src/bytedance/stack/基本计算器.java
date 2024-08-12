package bytedance.stack;

import java.util.Stack;

public class 基本计算器 {
    Stack<Integer> operandStack = new Stack<>();
    Stack<OPERATOR> operatorStack = new Stack<>();
    public int calculate(String s) {
        // 去除所有空格
        s = s.replaceAll(" ", "");
        int index = 0, len = s.length();
        while (index < len) {
            char ch = s.charAt(index);
            // 操作数
            if (Character.isDigit(ch)) {
                int operand = 0;
                while (index < len && Character.isDigit(s.charAt(index))) {
                    operand = operand * 10 + (s.charAt(index) - '0');
                    index++;
                }
                operandStack.push(operand);
            } else {
                if (ch == '+') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != OPERATOR.LEFT_PAREN) {
                        compute(operatorStack.pop());
                    }
                    operatorStack.push(OPERATOR.ADD);
                } else if (ch == '(') {
                    operatorStack.push(OPERATOR.LEFT_PAREN);
                } else if (ch == ')') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != OPERATOR.LEFT_PAREN) {
                        compute(operatorStack.pop());
                    }
                    operatorStack.pop();
                } else if (ch == '-') {
                    if (index == 0 || s.charAt(index - 1) == '(') {
                        operatorStack.push(OPERATOR.UNARY_SUB);
                    } else {
                        while (!operatorStack.isEmpty() && operatorStack.peek() != OPERATOR.LEFT_PAREN) {
                            compute(operatorStack.pop());
                        }
                        operatorStack.push(OPERATOR.SUB);
                    }
                }
                index++;
            }
        }
        while (!operatorStack.isEmpty()) {
            OPERATOR operator = operatorStack.pop();
            switch (operator) {
                case OPERATOR.ADD:
                case OPERATOR.SUB:
                case OPERATOR.UNARY_SUB:
                    compute(operator);
                    break;
                default: {
                    break;
                }
            }
        }
        return operandStack.pop();
    }

    private void compute(OPERATOR op) {
        switch (op) {
            case OPERATOR.ADD -> {
                int opr = operandStack.pop();
                int opl = operandStack.pop();
                operandStack.push(opl + opr);
            }
            case OPERATOR.SUB -> {
                int opr = operandStack.pop();
                int opl = operandStack.pop();
                operandStack.push(opl - opr);
            }
            case OPERATOR.UNARY_SUB -> operandStack.push(-operandStack.pop());
            default -> {}
        }
    }

    enum OPERATOR {
        ADD,
        SUB,
        UNARY_SUB,
        LEFT_PAREN
    }
}
