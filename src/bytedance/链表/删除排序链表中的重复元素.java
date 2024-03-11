package bytedance.链表;

import LeetCode.ListNode;

public class 删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = head;
        while (prev.next != null) {
            ListNode temp = prev.next;
            if (temp.val == prev.val) {
                prev.next = temp.next;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }
}
