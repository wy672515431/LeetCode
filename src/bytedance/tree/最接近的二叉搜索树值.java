package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class 最接近的二叉搜索树值 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> inorder = inorder(root);
        int index = binarySearch(inorder, target);
        int size = 0;
        int index1, index2;
        if (index >= 0) {
            ans.add(inorder.get(index));
            index1 = index - 1;
            index2 = index + 1;
            size++;
        } else {
            index = - index - 1;
            index1 = index - 1;
            index2 = index;
        }
        while (index1 >= 0 && index2 < inorder.size() && size < k) {
            int val1 = inorder.get(index1);
            int val2 = inorder.get(index2);
            if (target - val1 < val2 - target) {
                ans.add(val1);
                index1--;
            } else {
                ans.add(val2);
                index2++;
            }
            size++;
        }
        while (index1 >= 0 && size < k) {
            ans.add(inorder.get(index1--));
            size++;
        }
        while (index2 < inorder.size() && size < k) {
            ans.add(inorder.get(index2++));
            size++;
        }
        return ans;
    }

    private List<Integer> inorder(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
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

    private int binarySearch(List<Integer> inorder, double target) {
        int low = 0;
        int high = inorder.size() - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int val = inorder.get(mid);
            if (val == target) {
                return mid; // key found
            } else if (val < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // -(insert point) - 1 = index -> insert point = -index - 1
        return - (low + 1);
    }

}
