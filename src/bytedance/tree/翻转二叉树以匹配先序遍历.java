package bytedance.tree;

import LeetCode.TreeNode;
import LeetCode.翻转矩阵后的得分;

import java.util.ArrayList;
import java.util.List;

public class 翻转二叉树以匹配先序遍历 {
    int index = 0;
    List<Integer> ans = new ArrayList<>();

    /**
     *
     * @param root 二叉树根节点
     * @param voyage 列表，代表期望的二叉树先序遍历
     * @return 如果可以通过翻转完成，则返回翻转的节点列表，否则返回-1
     */
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        solve(root, voyage);
        if (!ans.isEmpty() && ans.getFirst() == -1) {
            ans.clear();
            ans.add(-1);
        }

        return ans;
    }


    // 进行先序遍历，遍历到某一节点时，如果节点值和voyage[i]不匹配，则不能完成
    // 如果匹配，则可以尝试进行翻转，如果左节点的值和voyage[i + 1]不匹配
    private void solve(TreeNode root, int[] voyage) {
        if (root == null) {
            return;
        }
        if (root.val != voyage[index++]) {
            ans.clear();
            ans.add(-1);
            return;
        }
        // 判断左节点值是否和voyage[index]匹配，如果不匹配，则翻转
        if (root.left != null && root.left.val != voyage[index]) {
            ans.add(root.val);
            solve(root.right, voyage);
            solve(root.left, voyage);
        } else {
            solve(root.left, voyage);
            solve(root.right, voyage);
        }
    }
}
