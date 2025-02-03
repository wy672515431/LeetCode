package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉树直径 {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        solve(root);
        return ans;
    }

    /**
     * 返回以root为根节点的树的最大深度
     * @param root
     * @return
     */
    private int solve(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = solve(root.left);
        int right = solve(root.right);
        ans = Math.max(ans, left + right);
        return Math.max(left, right) + 1;
    }
}
