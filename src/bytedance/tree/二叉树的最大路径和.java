package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉树的最大路径和 {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        solve(root);
        return ans;
    }

    public int solve(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树的最大值，小于0没有任何意义
        int left = Math.max(0, solve(root.left));
        // 右子树的最大值
        int right = Math.max(0, solve(root.right));
        ans = Math.max(ans, left + right + root.val);
        // 路径，只能选取左右子树的一条
        return Math.max(0, Math.max(left, right) + root.val);
    }
}
