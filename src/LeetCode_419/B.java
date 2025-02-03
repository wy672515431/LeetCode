package LeetCode_419;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class B {
    List<Integer> res = new ArrayList<>();
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        dfs(root);
        res.sort((a, b) -> b - a);
        if (k > res.size()) {
            return -1;
        }
        return res.get(k - 1);
    }

    public Status dfs(TreeNode root) {
        if (root == null) {
            return new Status(true, 0);
        }
        Status left = dfs(root.left);
        Status right = dfs(root.right);
        if (left.isPerfect && right.isPerfect && left.size == right.size) {
            res.add(left.size + right.size + 1);
            return new Status(true, left.size + right.size + 1);
        }
        return new Status(false, 0);
    }

    static class Status {
        boolean isPerfect;
        int size;
        Status(boolean isPerfect, int size) {
            this.isPerfect = isPerfect;
            this.size = size;
        }
    }
}
