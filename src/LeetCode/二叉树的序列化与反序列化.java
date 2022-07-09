package LeetCode;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class 二叉树的序列化与反序列化 {
    private static final String LEFT_NODE = "left";
    private static final String RIGHT_NODE = "right";
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        HashMap<TreeNode, Integer> mMap = new HashMap<>();
        HashMap<TreeNode, String> mMap1 = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int parentIndex = -1;
        int curIndex = 0;
        queue.add(root);
        mMap.put(root, parentIndex);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            //值-对应的父节点
            sb.append(curNode.val);
            sb.append('|');
            sb.append(mMap.get(curNode));
            sb.append("|");
            sb.append(curIndex);
            if (mMap1.get(curNode) != null) {
                sb.append("|");
                sb.append(mMap1.get(curNode));
            }
            sb.append(',');
            parentIndex++;
            curIndex++;
            if (curNode.left != null) {
                mMap.put(curNode.left, parentIndex);
                mMap1.put(curNode.left, LEFT_NODE);
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                mMap.put(curNode.right, parentIndex);
                mMap1.put(curNode.right, RIGHT_NODE);
                queue.offer(curNode.right);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] treeNodeStrings = data.split(",");
        HashMap<TreeNode, Integer> mMap = new HashMap<>();
        TreeNode root = new TreeNode();
        // 0 -val 1 - parentIndex 2 - curIndex 3 - left or right
        String[] treeNode = treeNodeStrings[0].split("\\|");
        root.val = Integer.parseInt(treeNode[0]);
        mMap.put(root, Integer.parseInt(treeNode[2]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (index >= treeNodeStrings.length)
                break;
            String[] temNode = treeNodeStrings[index].split("\\|");
            while (Integer.parseInt(temNode[1]) == mMap.get(curNode)) {
                int val = Integer.parseInt(temNode[0]);
                int curIndex = Integer.parseInt(temNode[2]);
                String position = temNode[3];
                TreeNode childNode = new TreeNode();
                childNode.val = val;
                if (position.equals(LEFT_NODE)) {
                    curNode.left = childNode;
                } else {
                    curNode.right = childNode;
                }
                mMap.put(childNode, curIndex);
                index++;
                queue.offer(childNode);
                if (index < treeNodeStrings.length) {
                    temNode = treeNodeStrings[index].split("\\|");
                } else {
                    break;
                }
            }
        }
        return root;
    }
}
