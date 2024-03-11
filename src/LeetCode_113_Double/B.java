package LeetCode_113_Double;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class B {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        priorityQueue.addAll(map.values());
        int result = 0;
        while (priorityQueue.size() >= 2) {
            int x = priorityQueue.poll();
            int y = priorityQueue.poll();
            x -= 1;
            y -= 1;
            if (x > 0) {
                priorityQueue.offer(x);
            }
            if (y > 0) {
                priorityQueue.offer(y);
            }
        }
        while (!priorityQueue.isEmpty()) {
            result += priorityQueue.poll();
        }
        return result;
    }

}
