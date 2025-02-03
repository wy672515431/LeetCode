package bytedance.链表;

import LeetCode.ListNode;

public class 相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 假设两个链表相交，那么相交点之后的长度是相同的
        // 设相交点之后的长度为x，headA前面的长度为a，headB前面的长度为b
        // lenA = a + x, lenB = b + x
        // 不妨设 lenA < lenB, 则当A走完时，b还剩 b - a的长度没有走完
        // 此时将a指向headB，继续走，当B走完时，a还剩 b + x - (b - a) = a + x没走完
        // 此时将b指向headA，二者目前的长度都是 a + x，同时走，必将在相交点相遇
        ListNode a = headA, b = headB;
        while (a != b) {
            // 相交比在交点相遇，最多经过两次遍历
            // 不相交，则a和b最后都会指向null
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}