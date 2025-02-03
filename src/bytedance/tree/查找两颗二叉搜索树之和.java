package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 查找两颗二叉搜索树之和 {
    // Hash即可解决
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> inorder1 = inorder(root1);
        List<Integer> inorder2 = inorder(root2);
        int left = 0, right = inorder2.size() - 1;
        while (left < inorder1.size() && right > 0) {
            int val1 = inorder1.get(left);
            int val2 = inorder2.get(right);
            if (val1 + val2 == target) {
                return true;
            } else if (val1 + val2 < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    private List<Integer> inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        List<Integer> inorder = new ArrayList<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            inorder.add(cur.val);
            cur = cur.right;
        }
        return inorder;
    }
}
