package LeetCode;

public class 重排列表 {
    //关键就是获得链表的中间节点
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode slowNode = head;
        ListNode fastNode = head;
        //双指针法获得中间节点
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        //将slowNode后面的链表翻转
        ListNode head1 = reverseList(slowNode.next);
        slowNode.next = null;
        ListNode cur = head;
        while (cur != null && head1 != null) {
            ListNode tem = cur.next;
            ListNode tem1 = head1.next;

            cur.next = head1;
            head1.next = tem;
            cur = tem;
            head1 = tem1;
        }

    }
    // 1 -> 2 -> 3
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tem = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tem;
        }
        return prev;
    }
}
