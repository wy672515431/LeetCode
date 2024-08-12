package bytedance.tree;

import LeetCode.TreeNode;

public class 根节点到叶节点数字之和 {
    int ans = 0;
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        solve(root);
        return ans;
    }

    private void solve(TreeNode root) {
        if (root == null) {
            return;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            ans += sum;
        }
        solve(root.left);
        solve(root.right);
        sum = (sum - root.val) / 10;
    }

}
