package LeetCode;

public class k个一组翻转链表 {
    // 1 -> 2 -> 3 -> 4 -> 5 -> 6
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1)
            return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        int length = 0;
        ListNode tem = head;
        while (tem != null) {
            length++;
            tem = tem.next;
        }
        int cnt = length / k;
        ListNode startNode = dummy;
        ListNode firstNode = dummy.next;
        ListNode secondNode = dummy.next.next;
        while (cnt > 0) {
            for (int i = 0; i < k - 1; i++) {
                ListNode tem1 = secondNode.next;
                secondNode.next = firstNode;
                firstNode = secondNode;
                secondNode = tem1;
            }
            cnt--;
            ListNode tem1 = startNode.next;
            startNode.next.next = secondNode;
            startNode.next = firstNode;
            if (cnt > 0) {
                startNode = tem1;
                firstNode = startNode.next;
                secondNode = startNode.next.next;
            }
        }
        return dummy.next;
    }



    // 1 -> 2 -> 3 -> 4  dummy -> 2 -> 1 -> 3 -> 4  dummy -> 3 -> 2 -> 1 -> 4
    public ListNode reverseGroup1(ListNode head, int k) {
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
                tem = cur.next;
                cur.next = tem.next;
                tem.next = prev.next;
                prev.next = tem;
            }
            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
