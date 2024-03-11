package LeetCode_346;

import java.util.Stack;

public class A {
    public static void main(String[] args) {
        System.out.println(minLength("ACBBD"));
    }
    public static int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        int cutLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'A' || ch == 'C') {
                stack.push(ch);
            } else if (ch == 'B') {
                if (!stack.isEmpty() && stack.peek() == 'A') {
                    cutLen += 2;
                    stack.pop();
                } else {
                    stack.clear();
                }
            } else if (!stack.isEmpty() && ch == 'D') {
                if (stack.peek() == 'C') {
                    cutLen += 2;
                    stack.pop();
                } else {
                    stack.clear();
                }
            } else {
                stack.clear();
            }
        }
        return len - cutLen;
    }
}
