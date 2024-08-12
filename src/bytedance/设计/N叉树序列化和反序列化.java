package bytedance.设计;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class N叉树序列化和反序列化 {
    public static void main(String[] args) {

    }
}

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                sb.append(serialize(child));
            }
        }
        sb.append(']');
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        int len = data.length();
        for (int i = 0; i < len; i++) {
            char ch = data.charAt(i);
            if (ch == '[') {
                int index = i + 1;
                int val = 0;
                while (index < len && Character.isDigit(data.charAt(index))) {
                    val = val * 10 + data.charAt(index) - '0';
                    index++;
                }
                Node node = new Node(val, new ArrayList<>());
                if (stack.isEmpty()) {
                    stack.push(node);
                } else {
                    stack.peek().children.add(node);
                    stack.push(node);
                }
            } else if (ch == ']') {
                if (stack.size() > 1) {
                    stack.pop();
                }
            }
        }
        return stack.pop();
    }



}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
