package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class 验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        // 左子树和右子树都是二叉搜索树
        // 对于每一个节点，我们要找到其值的取值范围
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 判断当前节点是否是二叉搜索树
     * 对于左子树来说，它的取值范围是(min, root.val)
     * 对于右子树来说，它的取值范围是(root.val, max)
     * @param root 当前节点
     * @param min  当前节点的最小值 exclusive
     * @param max  当前节点的最大值 exclusive
     * @return    是否是二叉搜索树
     */
    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        boolean left = isValidBST(root.left, min, root.val);
        boolean right = isValidBST(root.right, root.val, max);
        return left && right;
    }

    public boolean isValidBST1(TreeNode root) {
        // 中序遍历升序则是二叉搜索树
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 中序遍历
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }

}
