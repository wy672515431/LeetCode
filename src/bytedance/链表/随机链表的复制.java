package bytedance.链表;

import java.util.HashMap;
import java.util.Map;

public class 随机链表的复制 {
    public Node copyRandomList(Node head) {
        // A -> A' -> B -> B' -> C -> C'
        if (head == null) {
            return null;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node copy = node.next;
            copy.random = (node.random != null) ? node.random.next : null;
        }

        // A -> A' -> B -> B' -> C -> C'
        Node newHead = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node copy = node.next;
            // 恢复原来的list
            node.next = copy.next;
            copy.next = (copy.next != null) ? copy.next.next : null;
        }
        return newHead;
    }

    Map<Node, Node> cachedNode = new HashMap<>();
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node copy = new Node(head.val);
            cachedNode.put(head, copy);
            copy.next = copyRandomList2(head.next);
            copy.random = copyRandomList2(head.random);
        }
        return cachedNode.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
