package bytedance.链表;

import LeetCode.ListNode;

public class 环形链表II {
    public ListNode detectCycle(ListNode head) {
        // 首先找到环中的相交点
        ListNode slowNode = head;
        // 跟找链表中点还是有不同的, 不能是head.next，我们要保证slowNode和fastNode在同一位置出发
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            // 设环的长度为L，环前面的长度为a
            // 此时设满节点走的路程为x，则快节点走的路程为2x
            // 2x - x = n * L -> x = n * L
            // 设环的起点到相交点的距离为y
            // a + y 必然时L的整数倍，此时将快节点放到head，快慢节点同时走，相遇的地方就是环的起点
            if (slowNode == fastNode) {
                // 找到
                fastNode = head;
                while (true) {
                    if (slowNode == fastNode) {
                        return slowNode;
                    }
                    fastNode = fastNode.next;
                    slowNode = slowNode.next;
                }
            }
        }
        return null;
    }
}
