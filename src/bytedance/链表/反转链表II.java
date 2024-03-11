package bytedance.链表;

import LeetCode.ListNode;

public class 反转链表II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        for (int i = 1; i < right; i++) {
            // 前进
            if (i < left) {
                prev = prev.next;
                cur = cur.next;
            } else {
                // i >= left && i <= right
                ListNode temp = cur.next;
                cur.next = temp.next;
                temp.next = prev.next;
                prev.next = temp;
            }
        }
        return dummy.next;
    }
}
