package LeetCode;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class 寻找重复的子树 {
    Map<String, TreeNode> map = new HashMap<>();
    Set<TreeNode> repeat = new HashSet<>();
    //k -- hash v - Pair first = node second = index
    Map<String, Pair<TreeNode, Integer>> optimizedMap = new HashMap<>();
    int index = 0;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        /*一种想法是序列化树，将每一颗树都序列化成字符串，
        并且保证相同的子树会被序列化成相同的字符串,
        不相同的子树会被序列化为不同的字符串
        我们可以使用hash表存取结果
        序列化的两个常用方法：
        1.采用层次遍历的方法进行序列化
        2.递归进行序列化 root(左子树序列化结果)(右子树序列化结果)

        我们也可以使用一个三元组来表示一颗树
        (x, l, r)
        x代表根节点的值，l代表左子树的编号, r代表右子树的编号
        只有出现新的子树后，节点编号才会加1
         */
        dfs(root);
        return new ArrayList<>(repeat);
    }

    public String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append('(');
        sb.append(dfs(root.left));
        sb.append(')');
        sb.append('(');
        sb.append(dfs(root.right));
        sb.append(')');

        //出现过
        if (map.containsKey(sb.toString())) {
            repeat.add(map.get(sb.toString()));
        } else {
            map.put(sb.toString(), root);
        }
        return sb.toString();
    }

    /**
     * 返回左子树的序列号
     * @param root
     * @return
     */
    public int optimizedDfs(TreeNode root) {
        //空子树的序号返回0即可
        if (root == null) {
            return 0;
        }
        int[] triple = {root.val, optimizedDfs(root.left), optimizedDfs(root.right)};
        //作为唯一的hash值
        String hash = Arrays.toString(triple);
        if (optimizedMap.containsKey(hash)) {
            //存在
            repeat.add(optimizedMap.get(hash).getKey());
            return optimizedMap.get(hash).getValue();
        } else {
            optimizedMap.put(hash, new ImmutablePair<>(root, ++index));
            return index;
        }
    }
}
