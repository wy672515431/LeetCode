package LeetCode_420;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D {
    int n;
    List<ArrayList<Integer>> tree = new ArrayList<>();
    Map<Integer, Integer> size = new HashMap<>();
    boolean[] res;
    String s;
    public boolean[] findAnswer(int[] parent, String s) {
        this.s = s;
        n = parent.length;
        res = new boolean[n];
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            tree.get(parent[i]).add(i);
        }
        // sort
        for (int i = 0; i < n; i++) {
            tree.get(i).sort(Comparator.comparingInt(a -> a));
        }
        String str = dfs(0);
        dfs1(0);
        // 马拉车算法

        return res;
    }

    private String dfs(int cur) {
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < tree.get(cur).size(); i++) {
            sb.append(dfs(tree.get(cur).get(i)));
        }
        sb.append(s.charAt(cur));
        return sb.toString();
    }

    private int dfs1(int cur) {
        int sum = 1;
        for (int i = 0; i < tree.get(cur).size(); i++) {
            sum += dfs1(tree.get(cur).get(i));
        }
        size.put(cur, sum);
        return sum;
    }
}
