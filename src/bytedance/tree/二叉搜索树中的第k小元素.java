package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class 二叉搜索树中的第k小元素 {
    public int kthSmallest(TreeNode root, int k) {
        // 迭代中序遍历
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }
}
