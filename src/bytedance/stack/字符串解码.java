package bytedance.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class 字符串解码 {
    int index = 0;
    public String decodeString(String s) {
        // 数字后面必须跟上[]
        return solve(s);
    }

    private String solve1(String s) {
        // 不采用递归，而是采用栈
        Stack<Integer> times = new Stack<>();
        Deque<String> strs = new ArrayDeque<>();
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (Character.isLetter(ch)) {
                StringBuilder sb = new StringBuilder();
                while (index < s.length() && Character.isLetter(s.charAt(index))) {
                    sb.append(s.charAt(index));
                    index++;
                }
                // deque的push 为 addFirst
                strs.addLast(sb.toString());
            } else if (Character.isDigit(ch)) {
                int time = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    time = time * 10 + (s.charAt(index) - '0');
                    index++;
                }
                times.push(time);
            } else if (ch == ']') {
                StringBuilder sb = new StringBuilder();
                int time = times.pop();
                while (!strs.isEmpty() && !strs.peekLast().equals("[")) {
                    // 要反转
                    sb.append(new StringBuilder(strs.pollLast()).reverse());
                }
                strs.pollLast();
                StringBuilder tmp = new StringBuilder();
                strs.addLast(String.valueOf(tmp.repeat(sb.reverse().toString(), time)));
                index++;
            } else {
                strs.addLast(String.valueOf(ch));
                index++;
            }
        }
        StringBuilder res = new StringBuilder();
        while (!strs.isEmpty()) {
            res.append(strs.pollFirst());
        }
        return res.toString();
    }

    private String solve(String s) {
        StringBuilder res = new StringBuilder();
        int times = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (Character.isLetter(ch)) {
                res.append(ch);
                index++;
            } else if (Character.isDigit(ch)) {
                while (Character.isDigit(s.charAt(index))) {
                    times = times * 10 + (s.charAt(index) - '0');
                    index++;
                }
            } else if (ch == '[') {
                index++;
                res.repeat(solve(s), times);
                times = 0;
            } else if (ch == ']') {
                index++;
                return res.toString();
            }
        }
        return res.toString();
    }
}
