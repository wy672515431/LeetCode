package bytedance.链表;

import LeetCode.ListNode;

public class 删除链表的倒数第N个结点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        for (int i = 0; i < len; i++) {
            if (i == len - n) {
                prev.next = cur.next;
                break;
            } else {
                prev = prev.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeNthFromEndWithoutLen(ListNode head, int n) {
        // 我们可以让一个节点先往前走n步
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        // 当fast == null时, slow.next就是要删掉的节点
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
