package bytedance.tree;

import LeetCode.TreeNode;

public class 恢复二叉搜索树 {
    // 二叉搜索树性质：中序遍历一定是升序的
    // 目前交换两个节点
    // 1 2 3 4 5 6  1 5 3 4 2 6
    // 会出现一个下降，
    TreeNode first, second, prev = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        // 按照道理应该升序
        if (root.val < prev.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        inorder(root.right);
    }
}
