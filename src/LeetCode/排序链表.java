package LeetCode;

import java.util.List;

public class 排序链表 {
    //merge sort
    //merge sort的本质是将两个已经有序的数组进行排序
    //比如a,b为两个有序数组
    //同数组归并排序一样，我们需要找到链表的中点
    //找到链表中点的方法，快慢指针。快指针移动两步，慢指针移动一步。
    //对两个链表递归进行排序
    //将两个链表进行合并
    //自顶向上
    //空间复杂度o(lgn)
    public ListNode sortList(ListNode head) {
        return mergesort(head, null);
    }

    public ListNode mergesort(ListNode head, ListNode tail) {
        //空直接返回
        if (head == null || head == tail) {
            return head;
        }
        //拆分列表
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //取中间节点
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != tail) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode != tail) {
                fastNode = fastNode.next;
            }
        }
        ListNode midNode = slowNode;
        ListNode head1 = mergesort(head, midNode);
        ListNode head2 = mergesort(midNode, tail);
        return merge(head1, head2);
    }


    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode();
        ListNode curNode = dummyNode;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curNode.next = head1;
                curNode = curNode.next;
                head1 = head1.next;
            } else {
                curNode.next = head2;
                curNode = curNode.next;
                head2 = head2.next;
            }
        }
        while (head1 != null) {
            curNode.next = head1;
            head1 = head1.next;
            curNode = curNode.next;
        }
        while (head2 != null) {
            curNode.next = head2;
            head2 = head2.next;
            curNode = curNode.next;
        }
        return dummyNode.next;
    }

    public ListNode sortListBottomToTop(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //获取链表长度
        int length = 0;
        ListNode temNode = head;
        while (temNode != null) {
            length++;
            temNode = temNode.next;
        }
        ListNode dummyNode = new ListNode(0, head);
        //对subLength的链表进行排序
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prevNode = dummyNode, curNode = dummyNode.next;
            while (curNode != null) {
                ListNode head1 = curNode;
                for (int i = 1; i < subLength && curNode.next != null; i++) {
                    curNode = curNode.next;
                }
                //下一个链表的头节点
                ListNode head2 = curNode.next;
                curNode.next = null;
                curNode = head2;
                for (int i = 1; i < subLength && curNode != null && curNode.next != null; i++) {
                    curNode = curNode.next;
                }
                ListNode next = null;
                if (curNode != null) {
                    next = curNode.next;
                    curNode.next = null;
                }
                ListNode sortedNode = merge(head1, head2);
                prevNode.next = sortedNode;
                while (prevNode.next != null) {
                    prevNode = prevNode.next;
                }
                curNode = next;
            }
        }
        return dummyNode.next;
    }

//    /**
//     * 寻找链表的中点
//     * @param head
//     * @return
//     */
//    public ListNode getMidNode(ListNode head) {
//        ListNode slowNode = head;
//        ListNode fastNode = head.next;
//        while (fastNode != null && fastNode.next != null) {
//            slowNode = slowNode.next;
//            fastNode = fastNode.next.next;
//        }
//        return slowNode;
//    }
}
