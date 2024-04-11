package bytedance.tree;

import LeetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 从中序与后序遍历序列构造二叉树 {
    /**
     *
     * @param inorder 左 根 右
     * @param postorder 左 右 根
     * @return
     */
    Map<Integer, Integer> map = new HashMap<>();
    int n;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, n - 1, postorder, 0, n - 1);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                              int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        // [inStart, index - 1] 为左子树，[index + 1, inEnd] 为右子树
        int index = map.get(rootVal);
        int rightTreeLen = inEnd - index;
        TreeNode right = buildTree(inorder, index + 1, inEnd,
                postorder, postEnd - rightTreeLen, postEnd - 1);
        TreeNode left = buildTree(inorder, inStart, index - 1,
                postorder, postStart, postEnd - rightTreeLen - 1);
        root.right = right;
        root.left = left;
        return root;
    }
}
