package bytedance.tree;

import LeetCode.TreeNode;

public class 子结构判断 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
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
