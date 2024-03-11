package bytedance.链表;

import LeetCode.ListNode;

public class 重排列表 {
    /**
     * L0 -> L1 -> ... ->Ln
     *
     * L0 -> Ln -> L1 -> Ln-1
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        // 1.找到中点
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        ListNode head1 = slowNode.next;
        slowNode.next = null;
        ListNode reversedHead1 = reverseList(head1);
        ListNode curNode = head;
        while (reversedHead1 != null) {
            ListNode temp = reversedHead1.next;
            ListNode temp1 = curNode.next;
            reversedHead1.next = curNode.next;
            curNode.next = reversedHead1;

            reversedHead1 = temp;
            curNode = temp1;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curNode = head;
        while (curNode.next != null) {
            ListNode temp = curNode.next;
            curNode.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }
}
