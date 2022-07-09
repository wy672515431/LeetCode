package LeetCode;

public class 排序的循环列表 {
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            ListNode newListNode = new ListNode(insertVal);
            newListNode.next = newListNode;
            return newListNode;
        }
        if (head.next == head) {
            ListNode newListNode = new ListNode(insertVal);
            head.next = newListNode;
            newListNode.next = head;
            return head;
        }
        ListNode preListNode = head;
        ListNode nextListNode = head.next;
        ListNode maxListNode = head;
        while (true) {
            if (preListNode.val <= insertVal && insertVal <= nextListNode.val) {
                ListNode newListNode = new ListNode(insertVal);
                newListNode.next = preListNode.next;
                preListNode.next = newListNode;
                return head;
            }
            preListNode = preListNode.next;
            nextListNode = nextListNode.next;
            if (maxListNode.val <= preListNode.val) {
                maxListNode = preListNode;
            }
            if (preListNode == head) {
                ListNode newListNode = new ListNode(insertVal);
                newListNode.next = maxListNode.next;
                maxListNode.next = newListNode;
                return head;
            }
        }
    }
}
