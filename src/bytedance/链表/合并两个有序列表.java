package bytedance.链表;

import LeetCode.ListNode;

public class 合并两个有序列表 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode tem1 = list1;
        ListNode tem2 = list2;
        ListNode dummy = new ListNode();
        ListNode curNode = dummy;
        while (tem1 != null && tem2 != null) {
            if (tem1.val <= tem2.val) {
                curNode.next = tem1;
                tem1 = tem1.next;
            } else {
                curNode.next = tem2;
                tem2 = tem2.next;
            }
            curNode = curNode.next;
        }

        while (tem1 != null) {
            curNode.next = tem1;
            tem1 = tem1.next;
            curNode = curNode.next;
        }

        while (tem2 != null) {
            curNode.next = tem2;
            tem2 = tem2.next;
            curNode = curNode.next;
        }

        return dummy.next;
    }
}
