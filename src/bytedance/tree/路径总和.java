package bytedance.tree;

import LeetCode.TreeNode;

public class 路径总和 {
    /**
     * 减去根节点的值，然后递归左右子树，到叶子节点时，判断sum是否等于叶子节点的值
     * 如果是，则代表存在
     * @param root
     * @param sum
     * @return
     */
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
