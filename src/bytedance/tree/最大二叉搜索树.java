package bytedance.tree;

import LeetCode.TreeNode;

public class 最大二叉搜索树 {
    int ans = 0;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        solve(root);
        return ans;
    }

    static class Node {
        int min;
        int max;
        int size;

        Node(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    private Node solve(TreeNode root) {
        if (root.left == null && root.right == null) {
            ans = Math.max(ans, 1);
            return new Node(root.val, root.val, 1);
        }
        boolean ok = true;
        int size = 1;
        int min = root.val;
        int max = root.val;
        if (root.left != null) {
            Node node = solve(root.left);
            // 大于左子树的最大值
            if (node.size != -1 && root.val > node.max) {
                size += node.size;
                min = node.min;
            } else {
                ok = false;
            }
        }
        if (root.right != null) {
            Node node = solve(root.right);
            if (node.size != -1 && root.val < node.min) {
                size += node.size;
                max = node.max;
            } else {
                ok = false;
            }
        }
        if (ok) {
            ans = Math.max(ans, size);
            return new Node (min, max, size);
        }
        return new Node(-1, -1, -1);
    }
}


