package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 二叉树的锯齿形层序遍历 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        boolean leftToRight = false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            leftToRight = !leftToRight;
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                deque.add(node.val);
            }
            List<Integer> list = new ArrayList<>();
            while (!deque.isEmpty()) {
                if (leftToRight) {
                    list.add(deque.pollFirst());
                } else {
                    list.add(deque.pollLast());
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
