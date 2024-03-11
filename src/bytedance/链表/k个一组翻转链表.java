package bytedance.链表;

import LeetCode.ListNode;

public class k个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode tem = head;
        dummy.next = head;
        int length = 0;
        while (tem != null) {
            length++;
            tem = tem.next;
        }
        for (int i = 0; i < length / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                // dummy -> 1 -> 2 -> 3
                //         cur  tem
                tem = cur.next;
                // dummy -> 1 -> 3
                //          2 -> 3
                cur.next = tem.next;
                // dummy -> 1 -> 3
                //          2 -> 1
                tem.next = prev.next;
                // dummy -> 2 -> 1 -> 3
                prev.next = tem;
                // dummy -> 2 -> 1 -> 3
                //               cur
            }
            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
