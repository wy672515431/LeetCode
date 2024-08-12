package bytedance.链表;

import LeetCode.ListNode;

public class 旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        k = k % len;
        rotate(dummy, 0, len - k - 1);
        rotate(dummy, len - k, len - 1);
        rotate(dummy, 0, len - 1);
        return dummy.next;
    }

    public ListNode rotateRight1(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        k = k % len;
        // 位于add位置的节点是新的头节点
        int add = len - k;
        if (add == len) {
            return head;
        }
        // 形成环
        cur.next = head;
        while (add-- > 0) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }

    // 旋转[left, right]区间的链表
    public void rotate(ListNode dummy, int left, int right) {
        if (left >= right) {
            return;
        }
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        for (int i = 0; i < right; i++) {
            if (i < left) {
                prev = prev.next;
                cur = cur.next;
            } else {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
        }
    }
}
