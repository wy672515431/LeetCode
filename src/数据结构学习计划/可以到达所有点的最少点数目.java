package 数据结构学习计划;

import java.util.ArrayList;
import java.util.List;

public class 可以到达所有点的最少点数目 {
    /**
     * 有向无环图，我们只用统计入度为0的节点即可
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] in = new int[n];
        for (int i = 0; i < edges.size(); i++) {
            in[edges.get(i).get(1)]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
