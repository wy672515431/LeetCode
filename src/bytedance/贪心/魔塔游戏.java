package bytedance.贪心;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class 魔塔游戏 {
    public int magicTower(int[] nums) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        long blood = 1;
        long damage = 0;
        for (int num : nums) {
            if (num >= 0) {
                blood += num;
            } else {
                damage -= num;
                pq.offer(num);
                if (damage >= blood) {
                    while (damage >= blood && !pq.isEmpty()) {
                        int min = pq.poll();
                        damage += min;
                        deque.offerFirst(min);
                        ans++;
                    }
                }
            }
        }
        blood -= damage;
        while (!deque.isEmpty()) {
            blood += deque.pollLast();
        }
        if (blood <= 0) {
            return -1;
        }
        return ans;
    }
}
