package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 二叉搜素数的最小绝对差值 {
    List<Integer> inorder = new ArrayList<>();
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < inorder.size(); i++) {
            min = Math.min(min, inorder.get(i) - inorder.get(i - 1));
        }
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        inorder.add(root.val);
        inorder(root.right);
    }
}
