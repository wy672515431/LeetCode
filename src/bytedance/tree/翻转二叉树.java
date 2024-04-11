package bytedance.tree;

import LeetCode.TreeNode;

/**
 * 翻转一棵二叉树。
 */
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
}
