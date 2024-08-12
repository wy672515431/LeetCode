package bytedance.tree;

import java.util.Stack;

public class 验证二叉搜索树的前序遍历序列 {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            // 此时节点是某个节点的右节点
            // 右子树的值必须大于根节点
            if (num <= min) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                // 最后弹出的为当前节点的根节点
                min = stack.pop();
            }
            // 节点的值小于栈顶，为左子树的值
            stack.push(num);
        }
        return true;
    }

    public boolean verifyPreorder1(int[] preorder) {
        // 将preorder当作stack
        int index = -1;
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num <= min) {
                return false;
            }
            while (index >= 0 && num > preorder[index]) {
                min = preorder[index--];
            }
            preorder[++index] = num;
        }
        return true;
    }
}
