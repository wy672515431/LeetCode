package LeetCode;

import java.util.*;

public class 为运算表达式设计优先级 {
    /**
     * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。
     * 2*3-4
     * 2
     * 2*(3 - 4) = -2
     * 2
     * 6
     * 2 - 2
     *
     * @param expression
     * @return
     */
    HashMap<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression.length() == 0) {
            return new ArrayList<>();
        }
        //如果存在
        if (map.containsKey(expression)) {
            return map.get(expression);
        }
        List<Integer> result = new ArrayList<>();
        int num = 0;
        int index = 0;
        while (index < expression.length() && !isOperation(expression.charAt(index))) {
            num = num * 10 + (expression.charAt(index) - '0');
            index++;
        }
        //全为数字
        if (index == expression.length()) {
            result.add(num);
            map.put(expression, result);
            return result;
        }
        for (int i = 0; i < expression.length(); i++) {
            if (isOperation(expression.charAt(i))) {
                List<Integer> result1 = diffWaysToCompute(expression.substring(0, i));
                List<Integer> result2 = diffWaysToCompute(expression.substring(i + 1));
                for (int j = 0; j < result1.size(); j++) {
                    for (int k = 0; k < result2.size(); k++) {
                        char op = expression.charAt(i);
                        int ans = calc(result1.get(j), result2.get(k), op);
                        result.add(ans);
                    }
                }
            }
        }
        map.put(expression, result);
        return result;
    }

    private int calc(int a, int b, char c) {
        switch (c) {
            case '+' :
                return a + b;
            case '-' :
                return a -b;
            case '*' :
                return a * b;
        }
        return -1;
    }


    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}
