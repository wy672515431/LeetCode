package LeetCode_410;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B {
    public static void main(String[] args) {
        int[][] edges = {{1, 0}, {3, 0}, {2, 3}};
        System.out.println(new B().countGoodNodes(edges));
    }
    int ans = 0;
    List<List<Integer>> tree = new ArrayList<>();
    boolean[] visited;
    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            tree.get(parent).add(child);
            tree.get(child).add(parent);
        }
        solve(0);
        return ans;
    }

    // 返回以root为根的子树节点
    private int solve(int root) {
        visited[root] = true;
        // 如果是叶子节点，则是一个好节点
        if (tree.get(root).size() == 1 && root != 0) {
            ans++;
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        int size = 0;
        for (int child : tree.get(root)) {
            if (visited[child]) {
                continue;
            }
            int temp = solve(child);
            size += temp;
            set.add(temp);
        }
        if (set.size() == 1) {
            ans++;
        }
        return size + 1;
    }
}
