package bytedance;

import java.util.PriorityQueue;

public class 执行K次操作后的最大分数 {
    public long maxKelements(int[] nums, int k) {
        Long ans = 0L;
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> o2 - o1
        );
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        while (k-- > 0) {
            Integer max = queue.poll();
            ans += max;
            queue.offer((max - 1) / 3 + 1);
        }
        return ans;
    }
}
