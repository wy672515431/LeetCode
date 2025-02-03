package bytedance.数组;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class 查找和最小的k对数字 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // 用来去重
        Set<Node> set = new HashSet<>();
        // 优先队列，按照pair和排序
        PriorityQueue<Node> queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> (nums1[o.x] + nums2[o.y])));
        queue.offer(new Node(0, 0));
        set.add(new Node(0, 0));
        // 当前选择的前n小的pair (a1, b1), (a2, b2), (a3, b3)...
        // 则 n + 1小的pair在(a1 + 1, b1), (a1, b1 + 1) (a2 + 1, b2)....中选择
        // 当前选择的为(ai, bi)，我们将(ai, bi + 1)和(ai + 1, bi)加入队列
        // 这样有一个问题是，可能会出现重复
        while (k > 0 && !queue.isEmpty()) {
            Node node = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[node.x]);
            list.add(nums2[node.y]);
            res.add(list);
            if (node.x + 1 < nums1.length && set.add(new Node(node.x + 1, node.y))) {
                queue.offer(new Node(node.x + 1, node.y));
            }
            if (node.y + 1 < nums2.length && set.add(new Node(node.x, node.y + 1))) {
                queue.offer(new Node(node.x, node.y + 1));
            }
            k--;
        }
        return res;
    }

    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
