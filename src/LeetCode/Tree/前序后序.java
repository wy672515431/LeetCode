package LeetCode.Tree;

import LeetCode.TreeNode;

import java.util.HashMap;

public class 前序后序 {
    // k - preoder v - position in postorder
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        int length = preorder.length;
        TreeNode root = build(preorder, 0, length - 1, postorder, 0, length - 1);
        return root;
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        // int length = map.get(preorder[preStart + 1]) - postStart + 1;
        // [preStart + 1, preStart + length];
        // [postStart, postStart + length - 1]
        int length = preStart + 1 > preEnd ? 0 : map.get(preorder[preStart + 1]) - postStart + 1;
        root.left = build(preorder, preStart + 1, preStart + length, postorder, postStart, postStart + length - 1);
        // [preStart + length + 1, preEnd]
        // [postStart + length, postEnd]
        root.right = build(preorder, preStart + length + 1, preEnd, postorder, postStart + length, postEnd);
        return root;
    }

    public static void main(String[] args) {
        前序后序 a = new 前序后序();
        int[] pre = {1,2,4,5,3,6,7};
        int[] post = {4,5,2,6,7,3,1};
        a.constructFromPrePost(pre, post);
    }
}
