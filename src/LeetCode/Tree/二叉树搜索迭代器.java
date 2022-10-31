package LeetCode.Tree;

import LeetCode.TreeNode;

import java.util.Stack;

public class 二叉树搜索迭代器 {
}

class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    //二叉搜索树的迭代中序遍历
    //左子树 -> 根 -> 右子树
    //我们先从根开始，一直遍历根的所有左节点，全部进栈。
    //然后先出栈，如果出栈的节点有右子树，则将右子树的左节点全部进栈。依次类推
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode cur = stack.pop();
        TreeNode node = cur.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return cur.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
