package bytedance.tree;

import LeetCode.TreeNode;

public class 完全二叉树的节点个数 {
    /**
     * 我们可以通过一直遍历左节点来获得树的深度h
     * 则树的节点个数在 2 ^ h ~ 2 ^ (h + 1) - 1之间
     * 我们可以通过二分法来进行实现
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode temp = root;
        while (temp.left != null) {
            level++;
            temp = temp.left;
        }
        int low = (1 << level), high = (1 << (level + 1)) - 1;
        int ans = 0;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (exists(root, level, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 我们知道对于完全二叉树
     * 左孩子节点编号 = 父节点编号 * 2
     * 右孩子节点编号 = 父节点编号 * 2 + 1
     * 我们举个例子根节点的编号为 1(二进制)
     * 则左节点的编号为 10，右节点的编号为11
     *
     * @param root 树的根节点
     * @param level 树的深度
     * @param k 最后一个节点的编号
     * @return
     */
    public boolean exists(TreeNode root, int level, int k) {
        //假设level = 2
        // k = 101
        // 1 0 1
        //   1 0
        //   左 右
        //第一次的操作 1 -> 10
        //第二次的操作 10 -> 101
        //忽略第一位 0表示向左1表示向右
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}
