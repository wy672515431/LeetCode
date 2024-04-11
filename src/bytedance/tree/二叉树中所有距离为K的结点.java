package bytedance.tree;

import LeetCode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 二叉树中所有距离为K的结点 {
    Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
    List<Integer> ans = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 建图
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            List<TreeNode> sons = graph.getOrDefault(parent, new ArrayList<>());
            if (parent.left != null) {
                queue.offer(parent.left);
                sons.add(parent.left);

                List<TreeNode> children = graph.getOrDefault(parent.left, new ArrayList<>());
                children.add(parent);
                graph.put(parent.left, children);
            }

            if (parent.right != null) {
                queue.offer(parent.right);
                sons.add(parent.right);

                List<TreeNode> children = graph.getOrDefault(parent.right, new ArrayList<>());
                children.add(parent);
                graph.put(parent.right, children);
            }

            graph.put(parent, sons);
        }

        queue.offer(target);
        int dis = 0;
        Set<TreeNode> isVisited = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (dis == k) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    ans.add(node.val);
                }
                break;
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    isVisited.add(node);
                    List<TreeNode> edges = graph.get(node);
                    for (TreeNode edge : edges) {
                        if (!isVisited.contains(edge)) {
                            queue.offer(edge);
                        }
                    }
                }
                dis++;
            }
        }
        return ans;
    }
}
