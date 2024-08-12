package bytedance.stack;

import java.util.Stack;

public class 基本计算器III {
    Stack<Integer> operandStack = new Stack<>();
    Stack<OPERATOR> operatorStack = new Stack<>();

    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        int index = 0, len = s.length();
        while (index < len) {
            if (Character.isDigit(s.charAt(index))) {
                int operand = 0;
                while (index < len && Character.isDigit(s.charAt(index))) {
                    operand = operand * 10 + s.charAt(index) - '0';
                    index++;
                }
                operandStack.push(operand);
            } else {
                char operator = s.charAt(index);
                if (operator == '(') {
                    operatorStack.push(OPERATOR.LEFT_PAREN);
                } else if (operator == '+' || operator == '-') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != OPERATOR.LEFT_PAREN) {
                        compute(operatorStack.pop());
                    }
                    operatorStack.push(operator == '+' ? OPERATOR.ADD : OPERATOR.SUB);
                } else if (operator == '*' || operator == '/') {
                    while (!operatorStack.isEmpty() &&
                            (operatorStack.peek() == OPERATOR.MUL || operatorStack.peek() == OPERATOR.DIV)) {
                        compute(operatorStack.pop());
                    }
                    operatorStack.push(operator == '*' ? OPERATOR.MUL : OPERATOR.DIV);
                } else {
                    while (operatorStack.peek() != OPERATOR.LEFT_PAREN) {
                        compute(operatorStack.pop());
                    }
                    operatorStack.pop();
                }
                index++;
            }
        }
        while (!operatorStack.isEmpty()) {
            compute(operatorStack.pop());
        }
        return operandStack.pop();
    }

    private void compute(OPERATOR op) {
        int opr = operandStack.pop();
        int opl = operandStack.pop();
        switch (op) {
            case ADD:
                operandStack.push(opl + opr);
                break;
            case SUB:
                operandStack.push(opl - opr);
                break;
            case MUL:
                operandStack.push(opl * opr);
                break;
            case DIV:
                operandStack.push(opl / opr);
                break;
            default:
                break;
        }
    }

    enum OPERATOR {
        ADD,
        SUB,
        MUL,
        DIV,
        LEFT_PAREN
    }
}
