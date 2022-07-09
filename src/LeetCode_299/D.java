package LeetCode_299;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D {
    /**
     * DFS时间戳
     * 在DFS过程中,维护一个全局时间戳clock,每访问一个节点,clock加一,对于每个节点，记录其进入节点和离开节点的时间戳in[x],out[x]
     * 若y时x的祖先节点，则满足 in[y] < in[x] <= out[y]
     * @param nums
     * @param edges
     * @return
     */
    List<Integer>[] tree;
    int clock;
    //分别代表以某个节点为根的子树的异或值和入时间戳与出时间戳
    int[] xor, in, out;
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        tree = new ArrayList[n];
        Arrays.setAll(tree, e -> new ArrayList<>());
        //init the tree
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            tree[start].add(end);
            tree[end].add(start);
        }
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        clock = 0;
        dfs(0, -1, nums);
        int ans = Integer.MAX_VALUE;
        //枚举
        //假设删掉的两条边为x1-y1,x2-y2
        for (int i = 2; i < n; i++) {
            int sum1 = 0, sum2 = 0, sum3 = 0;
            for (int j = 1; j < i; j++) {
                //第一种情况两条边在同一子树上面且y1是x2的祖先节点
                if (in[j] < in[i] && in[i] <= out[j] ) {
                    sum1 = xor[0] ^ xor[j];
                    sum2 = xor[j] ^ xor[i];
                    sum3 = xor[i];
                } else if (in[i] < in[j] && in[j] <= out[i]) {
                    sum1 = xor[0] ^ xor[i];
                    sum2 = xor[i] ^ xor[j];
                    sum3 = xor[j];
                } else {
                    sum1 = xor[0] ^ xor[i] ^ xor[j];
                    sum2 = xor[i];
                    sum3 = xor[j];
                }
                int maxSum = Math.max(sum1, Math.max(sum2, sum3));
                int minSum = Math.min(sum1, Math.min(sum2, sum3));
                ans = Math.min(ans, maxSum - minSum);
            }
        }
        return ans;
    }

    private void dfs(int x, int father, int[] nums) {
        in[x] = ++clock;
        xor[x] = nums[x];
        //子节点
        for (Integer next : tree[x]) {
            //不是父节点
            if (next != father) {
                dfs(next, x, nums);
                xor[x] ^= xor[next];
            }
        }
        out[x] = clock;
    }
}
