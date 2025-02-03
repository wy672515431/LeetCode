package bytedance.链表;

import LeetCode.ListNode;

public class 回文链表 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针找到中点
        ListNode slowNode = head, fastNode = head.next;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        fastNode = slowNode.next;
        slowNode.next = null;
        ListNode head2 = reverse(fastNode);
        // head.len == head2.len || head.len == head2.len + 1
        while (head2 != null) {
            if (head.val != head2.val) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head, next = cur.next;
        while (next != null) {
            cur.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
            next = cur.next;
        }
        return dummy.next;
    }

}
