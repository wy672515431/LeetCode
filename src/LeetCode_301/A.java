package LeetCode_301;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class A {
    public int fillCups(int[] amount) {
        int ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < amount.length; i++) {
            queue.offer(amount[i]);
        }
        while (!queue.isEmpty()) {
            int max = queue.poll();
            int mid = queue.poll();
            if (max == 0) {
                break;
            }
            if (mid == 0) {
                max = max - 1;
                ans++;
                queue.offer(max);
                queue.offer(mid);
            } else {
                max = max - 1;
                mid = mid - 1;
                ans++;
                queue.offer(max);
                queue.offer(mid);
            }
        }
        return ans;
    }
}
