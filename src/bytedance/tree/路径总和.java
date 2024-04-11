package bytedance.tree;

import LeetCode.TreeNode;

public class 路径总和 {
    int sum = 0;
    boolean ans = false;

    /**
     * 是否存在和为targetSum的路径
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        solve(root, targetSum);
        return ans;
    }

    private void solve(TreeNode node, int targetSum) {
        if (ans || node == null) {
            return;
        }
        sum += node.val;
        // 叶子节点
        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                ans = true;
            }
        }
        if (node.left != null) {
            solve(node.left, targetSum);
        }
        if (node.right != null) {
            solve(node.right, targetSum);
        }
        sum -= node.val;
    }

    public boolean betterHasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return betterHasPathSum(root.left, sum - root.val) || betterHasPathSum(root.right, sum - root.val);
    }
}
