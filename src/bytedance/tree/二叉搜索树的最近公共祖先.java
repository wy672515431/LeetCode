package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val > p.val && root.val < q.val) ||
                (root.val < p.val && root.val > q.val) ||
                root.val == p.val || root.val == q.val) {
            return root;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return lowestCommonAncestor(root.right, p, q);
    }
}
