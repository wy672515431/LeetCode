package bytedance.链表;

import LeetCode.ListNode;

public class 循环有序列表插入 {
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            ListNode res = new ListNode(insertVal);
            res.next = res;
            return res;
        }
        ListNode cur = head;
        while (true) {
            if (cur.val < cur.next.val) {
                // 递增
                if (cur.val <= insertVal && insertVal <= cur.next.val) {
                    ListNode insertListNode = new ListNode(insertVal);
                    insertListNode.next = cur.next;
                    cur.next = insertListNode;
                    break;
                }
                cur = cur.next;
            } else if (cur.val > cur.next.val) {
                // 递减
                // 最大值或者最小值
                if (insertVal <= cur.next.val) {
                    ListNode insertListNode = new ListNode(insertVal);
                    insertListNode.next = cur.next;
                    cur.next = insertListNode;
                    break;
                }
                if (insertVal >= cur.val) {
                    ListNode insertListNode = new ListNode(insertVal);
                    insertListNode.next = cur.next;
                    cur.next = insertListNode;
                    break;
                }
                cur = cur.next;
            } else {
                // 相等，如果全是相等，则插入
                if (cur.next == head) {
                    ListNode insertListNode = new ListNode(insertVal);
                    insertListNode.next = cur.next;
                    cur.next = insertListNode;
                    break;
                }
                cur = cur.next;

            }
        }
        return head;
    }
}
