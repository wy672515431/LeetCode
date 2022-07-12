package LeetCode_82_Double;

import LeetCode.TreeNode;

public class A {
    public boolean evaluateTree(TreeNode root) {
        return dfs(root);
    }

    public boolean dfs(TreeNode root) {
        //叶子节点
        if (root.left == null && root.right == null) {
            if (root.val == 0) {
                return false;
            } else {
                return true;
            }
        }
        int val = root.val;
        boolean leftVal  = dfs(root.left);
        boolean rightVal = dfs(root.right);
        if (val == 2) {
            return leftVal || rightVal;
        } else {
            return leftVal && rightVal;
        }
    }
}
