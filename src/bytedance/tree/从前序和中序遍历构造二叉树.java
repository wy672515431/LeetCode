package bytedance.tree;

import LeetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 从前序和中序遍历构造二叉树 {
    /**
     * 前序是根左右，中序是左根右
     * 没有重复元素，所以可以用map存储中序遍历的值和索引
     * @param preorder 前序
     * @param inorder 中序
     * @return
     */
    private Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder,
                          int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        // root
        TreeNode root = new TreeNode(rootVal);
        int index = map.get(rootVal);
        // [inStart, index - 1]是左子树, [index + 1, inEnd]是右子树
        TreeNode left = build(preorder, preStart + 1,
                preStart + index - inStart, inorder, inStart, index - 1);
        TreeNode right = build(preorder, preStart + index - inStart + 1,
                preEnd, inorder, index + 1, inEnd);
        root.left = left;
        root.right = right;
        return root;
    }
}
