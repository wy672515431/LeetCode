package LeetCode_317;

import LeetCode.TreeNode;

import java.util.HashMap;

public class D {
    /**
     * 我们移除某个节点后树的高度，就是忽略该节点时数的高度。
     * 通过对数的从左到右遍历和从右到左遍历，我们可以获得两种高度
     * 第一种是子树所在树的高度，另一种是子树所不在树的高度
     * @param root
     * @param queries
     * @return
     */
    HashMap<Integer, Integer> map1 = new HashMap<>();
    HashMap<Integer, Integer> map2 = new HashMap<>();
    int maxHeight = 0;
    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] ans = new int[queries.length];
        dfs1(root, 0);
        maxHeight = 0;
        dfs2(root, 0);
        for (int i = 0; i < queries.length; i++) {
            ans[i] = Math.max(map1.get(queries[i]), map2.get(queries[i]));
        }
        return ans;
    }

    public void dfs1(TreeNode node, int height) {
        if (node == null) {
            return;
        }
        //遍历到该节点时的，最大高度
        map1.put(node.val, maxHeight);
        maxHeight = Math.max(maxHeight, height);
        dfs1(node.left, height + 1);
        dfs1(node.right, height + 1);
    }

    public void dfs2(TreeNode node, int height) {
        if (node == null) {
            return;
        }
        //遍历到该节点时的，最大高度
        map2.put(node.val, maxHeight);
        maxHeight = Math.max(maxHeight, height);
        dfs2(node.right, height + 1);
        dfs2(node.left, height + 1);
    }

}
