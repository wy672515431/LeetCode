package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class 任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(node-> node.cnt, Comparator.reverseOrder()));
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.offer(node);
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            //在n+1的时间内执行的任务数
            int taskNum = queue.size() <= n ? queue.size() : n + 1;
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < taskNum; i++) {
                list.add(queue.poll());
            }
            list.forEach(node -> {
                node.cnt--;
                if (node.cnt > 0) {
                    queue.offer(node);
                }
            });
            if (queue.isEmpty()) {
                ans += taskNum;
                break;
            } else {
                ans += n;
            }
        }
        return ans;
    }

    class Node{
        char task;
        int cnt;
        public Node(char task, int cnt) {
            this.task = task;
            this.cnt = cnt;
        }
    }
}

