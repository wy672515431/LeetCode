package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class BST两数之和 {
    public boolean findTarget(TreeNode root, int k) {
        HashMap<Integer, Integer> mMap = new HashMap();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int val = mMap.getOrDefault(node.val, 0);
            mMap.put(node.val, val + 1);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(mMap.containsKey(k - node.val)) {
                if(!(k - node.val == node.val && mMap.get(node.val) == 1))
                    return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }
}


