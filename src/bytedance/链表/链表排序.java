package bytedance.链表;

import LeetCode.ListNode;

public class 链表排序 {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    public ListNode mergeSort(ListNode head) {
        // 找到中点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slowNode = head, fastNode = head.next;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        // 拆分
        ListNode latterHead = slowNode.next;
        slowNode.next = null;

        return merge(mergeSort(head), mergeSort(latterHead));
    }

    // 将两个有序的列表合并
    public ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy, tem1 = head1, tem2 = head2;
        dummy.next = cur;
        while (tem1 != null && tem2 != null) {
            int val1 = tem1.val, val2 = tem2.val;
            if (val1 < val2) {
                cur.next = tem1;
                tem1 = tem1.next;
            } else {
                cur.next = tem2;
                tem2 = tem2.next;
            }
            cur = cur.next;
        }

        // 简化 cur.next = tem1 != null ? tem1 : tem2;
        while (tem1 != null) {
            cur.next = tem1;
            tem1 = tem1.next;
            cur = cur.next;
        }
        while (tem2 != null) {
            cur.next = tem2;
            tem2 = tem2.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode mergeSortFromBottomToTop(ListNode head) {
        // 我们可以将链表拆分为长度为1的n条，每两两一合并，然后再合并长度为2的n / 2条，依次类推
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode temNode = head;
        while (temNode != null) {
            length++;
            temNode = temNode.next;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            // 要找到两个链表的头节点
            ListNode prevNode = dummy, curNode = prevNode.next;
            while (curNode != null) {
                // 第一个列表的头节点
                ListNode head1 = curNode;
                for (int i = 1; i < subLength && curNode.next != null; i++) {
                    curNode = curNode.next;
                }
                // 第二个链表的头节点
                ListNode head2 = curNode.next;
                // 截断
                curNode.next = null;
                curNode = head2;
                // 第二个链表也需要截断、此时curNode可能为null
                for (int i = 1; i < subLength && curNode != null && curNode.next != null; i++) {
                    curNode = curNode.next;
                }
                ListNode next = null;
                // 截断
                if (curNode != null) {
                    next = curNode.next;
                    curNode.next = null;
                }
                prevNode.next = merge(head1, head2);
                while (prevNode.next != null) {
                    prevNode = prevNode.next;
                }
                curNode = next;
            }
        }

        return dummy.next;
    }
}
