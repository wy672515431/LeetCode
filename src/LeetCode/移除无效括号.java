package LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class 移除无效括号 {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack();
        Stack<Character> chStack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (s.charAt(i) == '(') {
                stack.push(i);
                chStack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (!chStack.isEmpty() && chStack.peek() == '(') {
                    stack.pop();
                    chStack.pop();
                } else {
                    stack.push(i);
                    chStack.push(s.charAt(i));
                }
            }
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            sb.deleteCharAt(index);
        }
        return sb.toString();
    }

    /**
     * 复杂度O(n)
     * @param s
     * @return
     */
    public String minRemoveToMakeValid1(String s) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> indexToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            indexToRemove.add(stack.pop());
        }
        for(int i = 0; i < s.length(); i++) {
            if (!indexToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
