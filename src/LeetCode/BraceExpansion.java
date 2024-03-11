package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// TODO: as a classical problem
/**
 * 1.求积的优先级高于求并的优先级
 * 用expr代表任意一种表达式,term代表最外层没有逗号分割的表达式
 * expr = term | term, expr
 * term可以由英文字母组成或者花括号表示
 * term = item | item term
 * item = letter | {expr}
 */
public class BraceExpansion {
    String expression;
    int idx;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.idx = 0;
        Set<String> ret = expr();
        return new ArrayList<>(ret);
    }

    // item . letter | { expr }
    private Set<String> item() {
        Set<String> ret = new TreeSet<>();
        if (expression.charAt(idx) == '{') {
            idx++;
            ret = expr();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(expression.charAt(idx));
            ret.add(sb.toString());
        }
        idx++;
        return ret;
    }

    // term . item | item term
    private Set<String> term() {
        // 初始化空集合，与之后的求解结果求笛卡尔积
        Set<String> ret = new TreeSet<>() {{
            add("");
        }};
        // item 的开头是 { 或小写字母，只有符合时才继续匹配
        while (idx < expression.length() && (expression.charAt(idx) == '{' || Character.isLetter(expression.charAt(idx)))) {
            Set<String> sub = item();
            Set<String> tmp = new TreeSet<>();
            for (String left : ret) {
                for (String right : sub) {
                    tmp.add(left + right);
                }
            }
            ret = tmp;
        }
        return ret;
    }

    // expr . term | term, expr
    private Set<String> expr() {
        Set<String> ret = new TreeSet<>();
        while (true) {
            // 与 term() 求解结果求并集
            ret.addAll(term());
            // 如果匹配到逗号则继续，否则结束匹配
            if (idx < expression.length() && expression.charAt(idx) == ',') {
                idx++;
                continue;
            } else {
                break;
            }
        }
        return ret;
    }

    /**
     * 我们知道求并集的优先级低于求笛卡尔积的优先级
     * 我们不妨将求并集的操作当成加法操作，将求笛卡尔积的操作当成乘法操作。
     * 类似于用栈求后缀表达式
     * {a,b}{c,{d,e}} = {a, b} x {c + {d, e}}
     * @param expression
     * @return
     */
    public List<String> braceExpansionI(String expression) {
        // 操作符栈
        Deque<Character> op = new ArrayDeque<>();
        // 操作数
        List<Set<String>> stk = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ',') {
                // 不断地弹出栈顶运算符，直到栈为空或者栈顶不为乘号
                while (!op.isEmpty() && op.peek() == '*') {
                    ope(op, stk);
                }
                op.push('+');
            } else if (expression.charAt(i) == '{') {
                // 首先判断是否需要添加乘号，再将 { 添加到运算符栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                op.push('{');
            } else if (expression.charAt(i) == '}') {
                // 不断地弹出栈顶运算符，直到栈顶为 {
                while (!op.isEmpty() && op.peek() != '{') {
                    ope(op, stk);
                }
                op.pop();
            } else {
                // 首先判断是否需要添加乘号，再将新构造的集合添加到集合栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(i));
                stk.add(new TreeSet<String>() {{
                    add(sb.toString());
                }});
            }
        }

        while (!op.isEmpty()) {
            ope(op, stk);
        }
        return new ArrayList<String>(stk.get(stk.size() - 1));
    }

    // 弹出栈顶运算符，并进行计算
    public void ope(Deque<Character> op, List<Set<String>> stk) {
        int l = stk.size() - 2, r = stk.size() - 1;
        if (op.peek() == '+') {
            stk.get(l).addAll(stk.get(r));
        } else {
            Set<String> tmp = new TreeSet<String>();
            for (String left : stk.get(l)) {
                for (String right : stk.get(r)) {
                    tmp.add(left + right);
                }
            }
            stk.set(l, tmp);
        }
        op.pop();
        stk.remove(stk.size() - 1);
    }

}
