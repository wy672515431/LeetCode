package bytedance.设计;

import LeetCode.TreeNode;

import java.util.ArrayList;

public class N叉树编码为二叉树 {
}


class Codec1 {
    // Encodes an n-ary tree to a binary tree.
    // 二叉树的左孩子是n叉树的第一个孩子，右孩子是n叉树的兄弟节点
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode binaryRoot = new TreeNode(root.val);
        if (root.children != null && !root.children.isEmpty()) {
            binaryRoot.left = encode(root.children.getFirst());
            TreeNode cur = binaryRoot.left;
            for (int i = 1; i < root.children.size(); i++) {
                cur.right = encode(root.children.get(i));
                cur = cur.right;
            }
        }
        return binaryRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node naryRoot = new Node(root.val, new ArrayList<>());
        if (root.left != null) {
            naryRoot.children.add(decode(root.left));
        }
        TreeNode cur = root.left;
        while (cur != null && cur.right != null) {
            naryRoot.children.add(decode(cur.right));
            cur = cur.right;
        }
        return naryRoot;
    }
}