package bytedance.tree;

import LeetCode.TreeNode;

public class 二叉树最长连续序列 {
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        solve(root);
        return ans;
    }

    private Node solve(TreeNode root) {
        // 左边递增 右边递减 ans = left + right + 1;
        // 左边递减 右边递增 ans = left + right + 1;
        // 左右两边相同 ans = Math.max(left, right) + 1;
        // 返回值要返回左边和右边
        // root.val == root.left.val - 1 , 左边递增的长度
        // root.val == root.left.val + 1 , 左边递减的长度
        if (root.left == null && root.right == null) {
            ans = Math.max(ans, 1);
            return new Node(1, 1);
        }
        if (root.left == null) {
            int dec = 1;
            int inc = 1;
            Node right = solve(root.right);
            if (root.val == root.right.val - 1) {
                inc += right.inc;
            } else if (root.val == root.right.val + 1) {
                dec += right.dec;
            }
            ans = Math.max(ans, Math.max(dec, inc));
            return new Node(dec, inc);
        } else if (root.right == null) {
            int dec = 1;
            int inc = 1;
            Node left = solve(root.left);
            if (root.val == root.left.val - 1) {
                inc += left.inc;
            } else if (root.val == root.left.val + 1) {
                dec += left.dec;
            }
            ans = Math.max(ans, Math.max(dec, inc));
            return new Node(dec, inc);
        } else {
            Node left = solve(root.left);
            Node right = solve(root.right);
            int dec = 0;
            int inc = 0;
            if (root.val == root.left.val - 1 && root.val == root.right.val + 1) {
                dec = Math.max(dec, right.dec);
                inc = Math.max(inc, left.inc);
                ans = Math.max(ans, left.inc + right.dec + 1);
            } else if (root.val == root.left.val + 1 && root.val == root.right.val - 1) {
                dec = Math.max(dec, left.dec);
                inc = Math.max(inc, right.inc);
                ans = Math.max(ans, left.dec + right.inc + 1);
            }

            if (root.val == root.left.val - 1) {
                inc = Math.max(inc, left.inc);
                ans = Math.max(ans, left.inc + 1);
            }
            if (root.val == root.left.val + 1) {
                dec = Math.max(dec, left.dec);
                ans = Math.max(ans, left.dec + 1);
            }
            if (root.val == root.right.val - 1) {
                inc = Math.max(inc, right.inc);
                ans = Math.max(ans, right.inc + 1);
            }
            if (root.val == root.right.val + 1) {
                dec = Math.max(dec, right.dec);
                ans = Math.max(ans, right.dec + 1);
            }
            return new Node(dec + 1, inc + 1);
        }
    }

    static class Node {
        int dec;
        int inc;
        public Node(int dec, int inc) {
            this.dec = dec;
            this.inc = inc;
        }
    }
}
