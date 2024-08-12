package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 寻找二叉树的叶子节点 {
    // 叶子节点的高度为0，非叶子节点的高度为左右子树的最大高度+1
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        solve(root);
        return res;
    }

    private int solve(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = solve(root.left);
        int rightHeight = solve(root.right);
        int height = Math.max(leftHeight, rightHeight) + 1;
        if (height >= res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        return height;
    }
}
