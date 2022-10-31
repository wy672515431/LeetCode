package LeetCode;

public class 修剪二叉搜索树 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        //根的值小于Low,左子树全部过滤
        //根的值大于high,右子树全部过滤
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        if (root == null) {
            return null;
        }
        //保证了根的值是在low,high之间的
        //如果左子树的根的值小于val,将左子树的右子树移接到左子树
        //否则
        for (TreeNode node = root; node.left != null; ) {
            if (node.left.val < low) {
                node.left = node.left.right;
            } else {
                //不用考虑大于High,因为前提已经保证
                node = node.left;
            }
        }
        for (TreeNode node = root; node.right != null; ) {
            if (node.right.val > high) {
                node.right = node.right.left;
            } else {
                node = node.right;
            }
        }
        return root;
    }
}
