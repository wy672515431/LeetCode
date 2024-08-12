package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉搜索树的最近公共祖先 {
    /**
     * 左边的节点值小于根节点的值，右边的节点值大于根节点的值
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val > p.val && root.val < q.val) ||
                (root.val < p.val && root.val > q.val) ||
                root.val == p.val || root.val == q.val) {
            return root;
        }
        // 都在左子树
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 否则都在右子树
        return lowestCommonAncestor(root.right, p, q);
    }
}
