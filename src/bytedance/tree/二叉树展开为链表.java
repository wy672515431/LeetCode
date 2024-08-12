package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉树展开为链表 {
    // 一种直观的思想
    // 前序遍历为 1 2 3 4 5 6
    // 在遍历到2的时候，将1的右节点指向2，但是这样会丢失1的右节点
    // 6 5 4 3 2 1 为前序遍历的逆序, 在遍历到5时，将6的右节点指向5
    // 这样不会丢失右节点，因为右节点已经遍历了
    TreeNode last = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }

    public void flatten1(TreeNode root) {
        // 1.将左子树插入到右子树
        // 2.将原来的右子树接到左子树的最右边节点
        // 考虑新右子树的根节点，重复过程
        while (root != null) {
            if (root.left != null) {
                TreeNode pre = root.left;
                // 找到左子树的最右边节点
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
