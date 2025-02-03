package bytedance.链表;

import LeetCode.ListNode;

public class 两两交换链表中的节点 {
    public ListNode swapPairs(ListNode head) {
        return reverseKGroup(head, 2);
    }

    private ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        int len = 0;
        ListNode tem = head;
        while (tem != null) {
            len++;
            tem = tem.next;
        }

        ListNode pre = dummy;
        ListNode cur = head;
        for (int i = 0; i < len / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
