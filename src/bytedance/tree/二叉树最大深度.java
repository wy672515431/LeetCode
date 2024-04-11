package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉树最大深度 {
    /**
     * 一个树的最大深度等于左右子树的最大深度+1
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }
}
