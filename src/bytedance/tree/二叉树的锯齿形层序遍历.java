package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
            int size = queue.size();
            Deque<Integer> level = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (leftToRight) {
                    level.offerLast(cur.val);
                } else {
                    level.offerFirst(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ans.add(new ArrayList<>(level));
        }
        return ans;
    }
}
