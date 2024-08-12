package bytedance.链表;

import LeetCode.ListNode;

public class 删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == next.val) {
                while (cur != null && next != null && cur.val == next.val) {
                    cur = cur.next;
                    next = next.next;
                }
                // 此时cur指向重复元素的最后一个元素
                // next指向重复元素的下一个元素
                // 删除重复元素
                prev.next = next;
            } else {
                // 移动prev指针
                prev = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
