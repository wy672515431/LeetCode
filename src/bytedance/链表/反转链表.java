package bytedance.链表;

import LeetCode.ListNode;

public class 反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode tem;
        dummy.next = head;
        while (cur.next != null) {
            tem = cur.next;
            cur.next = tem.next;
            tem.next = dummy.next;
            dummy.next = tem;
        }
        return dummy.next;
    }
}
