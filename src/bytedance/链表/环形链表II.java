package bytedance.链表;

import LeetCode.ListNode;

public class 环形链表II {
    public ListNode detectCycle(ListNode head) {
        // 首先找到环中的相交点
        ListNode slowNode = head;
        // 跟找链表中点还是有不同的
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            // 设不再环内的节点个数a, 在环内的节点个数b

            // 这里易知, 快节点走了2nb，慢节点走了nb
            // 从链表头出发，到链表环口需要走a + nb步，此时慢节点还需要走a步到环入口，此时只需要将快节点置为链表头，
            // 二者走a步，即可在环入口相遇。
            if (slowNode == fastNode) {
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
