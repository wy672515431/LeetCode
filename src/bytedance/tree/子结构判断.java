package bytedance.tree;

import LeetCode.TreeNode;

public class 子结构判断 {
    /**
     * 剑指 Offer 26. 树的子结构
     * 判断B是否是A的子结构
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // A或B为空，返回false
        if (A == null || B == null) {
            return false;
        }
        // 如果A的根节点和B的根节点相同，判断A是否包含B。否则，判断A的左右子树是否包含B.
        return solve(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 判断A是否包含B
     * @param A
     * @param B
     * @return
     */
    private boolean solve(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }
        return solve(A.left, B.left) && solve(A.right, B.right);
    }
}
