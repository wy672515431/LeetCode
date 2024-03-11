package bytedance.tree;

import LeetCode.TreeNode;

public class 翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
        solve(root);
        return root;
    }

    private void solve(TreeNode root) {
        if (root == null) {
            return;
        }
        solve(root.right);
        solve(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree1(root.left);
        TreeNode right = invertTree1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
