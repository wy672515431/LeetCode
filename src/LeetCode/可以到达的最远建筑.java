package LeetCode;

import java.util.PriorityQueue;

public class 可以到达的最远建筑 {
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int i = 0;
        for (; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            // 如果差值大于0，说明需要梯子和砖块
            if (diff > 0) {
                queue.offer(diff);
                if (bricks < diff) {
                    if (queue.isEmpty() && ladders > 0) {
                        ladders--;
                    } else if (!queue.isEmpty() && ladders > 0) {
                        bricks += queue.poll() - diff;
                        ladders--;
                    } else {
                        break;
                    }
                } else {
                    bricks -= diff;
                }
            }
        }
        return i;
    }
}
