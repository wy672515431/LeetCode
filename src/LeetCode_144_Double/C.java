package LeetCode_144_Double;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class C {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));
        // ans - 选择区间数
        // now - 当前点被覆盖的数
        int ans = 0, now = 0;
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[] diff = new int[n + 1];
        for (int i = 0, j = 0; i < n; i++) {
            // 减去在i之前的区间
            now -= diff[i];
            while (j < m && queries[j][0] <= i) {
                pq.offer(queries[j][1]);
                j++;
            }
            while (now < nums[i] && !pq.isEmpty()) {
                int end = pq.poll();
                if (end >= i) {
                    now++;
                    ans++;
                    diff[end + 1]++;
                }
            }
            if (now < nums[i]) {
                return -1;
            }
        }
        return m - ans;
    }
}
