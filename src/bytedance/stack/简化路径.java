package bytedance.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class 简化路径 {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        int index = 0, len = path.length();
        while (index < len) {
            char ch = path.charAt(index);
            if (ch == '/') {
                index++;
                continue;
            }
            if (ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (index < len && path.charAt(index) != '/') {
                    sb.append(path.charAt(index));
                    index++;
                }
                String temp = sb.toString();
                if (temp.equals(".")) {
                    // do nothing
                } else if (temp.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(temp);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                while (index < len && path.charAt(index) != '/') {
                    sb.append(path.charAt(index));
                    index++;
                }
                stack.push(sb.toString());
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            sb.append("/");
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
