package 数据结构学习计划;

import java.util.*;

public class 根据字符出现频率排序 {
    public String frequencySort(String s) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> o2.frequency - o1.frequency
        );
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer frequency = map.putIfAbsent(s.charAt(i), 0);
            if (frequency != null) {
                map.put(s.charAt(i), frequency + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Node newNode = new Node(entry.getKey(), entry.getValue());
            priorityQueue.offer(newNode);
        }
        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Node newNode = priorityQueue.poll();
            for (int i = 0; i < newNode.frequency; i++) {
                sb.append(newNode.ch);
            }
        }
        return sb.toString();
    }

    class Node {
        char ch;
        int frequency;
        public Node(char ch, int frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }
    }
}
