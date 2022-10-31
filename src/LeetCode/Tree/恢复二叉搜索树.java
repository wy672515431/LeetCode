package LeetCode.Tree;

import LeetCode.TreeNode;

public class 恢复二叉搜索树 {
    //对于二叉搜索树，其中序遍历得到的序列应该是升序的
    //交换的两个值破坏了序列的递增性
    //假设递增序列[1,2,3,4,5,6,7] -> [1,6,3,4,5,2,7]
    //我们可以发现6 和 2 这个节点
    //第一个是首先大于下个节点的值，第二个为最后一个小于上个节点的值
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        solve(root);
        int tem = second.val;
        second.val = first.val;
        first.val = tem;
    }

    public void solve(TreeNode root) {
        if (root == null) {
            return;
        }
        solve(root.left);
        if (root.val < prev.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        solve(root.right);
    }
}
