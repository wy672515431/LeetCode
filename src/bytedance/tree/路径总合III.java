package bytedance.tree;

import LeetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 路径总合III {
    // 朴素方法时间复杂度为O(n^2)，枚举每个节点作为起点
    // 采用前缀和的方法，时间复杂度为O(n)
    Map<Long, Integer> prefix = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        // 空节点的前缀和为0，出现一次
        prefix.put(0L, 1);
        return dfs(root, 0, targetSum);
    }

    private int dfs(TreeNode root, long cur, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        cur += root.val;
        // cur - pre = targetSum
        res += prefix.getOrDefault(cur - targetSum, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        res += dfs(root.left, cur, targetSum);
        res += dfs(root.right, cur, targetSum);
        // back
        prefix.put(cur, prefix.get(cur) - 1);
        return res;
    }
}
