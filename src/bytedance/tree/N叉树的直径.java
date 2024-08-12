package bytedance.tree;

import java.util.ArrayList;
import java.util.List;

public class N叉树的直径 {
    private int ans = 0;
    public int diameter(Node root) {
        solve(root);
        return ans;
    }

    private int solve(Node root) {
        if (root == null) {
            return 0;
        }
        // 选两个最长的
        int max1 = -1;
        int max2 = -1;
        for (Node child : root.children) {
            int depth = solve(child);
            if (depth > max1) {
                max2 = max1;
                max1 = depth;
            } else if (depth > max2) {
                max2 = depth;
            }
        }
        ans = Math.max(ans, max1 + max2 + 2);
        return 1 + max1;
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
