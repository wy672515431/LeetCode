package bytedance.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 克隆图 {
    Set<Integer> isVisited = new HashSet<>();
    // key - node, val - clone node
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // bfs
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        isVisited.add(node.val);
        map.put(node, new Node(node.val));
        // 在queue中的节点都是已经存在clone节点的
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node child : cur.neighbors) {
                if (!isVisited.contains(child.val)) {
                    isVisited.add(child.val);
                    map.put(child, new Node(child.val));
                    queue.offer(child);
                }
                map.get(cur).neighbors.add(map.get(child));
            }
        }
        return map.get(node);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
