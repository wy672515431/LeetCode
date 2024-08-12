package bytedance.tree;

import java.util.ArrayList;
import java.util.List;

public class 找到N叉树的根节点 {
    public Node findRoot(List<Node> tree) {
        // 根节点只出现过一次
        int sum = 0;
        for (Node node : tree) {
            sum += node.val;
            for (Node child : node.children) {
                sum -= child.val;
            }
        }
        for (Node node : tree) {
            if (node.val == sum) {
                return node;
            }
        }
        return null;
    }

    static class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
