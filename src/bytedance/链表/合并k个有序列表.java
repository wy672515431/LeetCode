package bytedance.链表;

import LeetCode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 合并k个有序列表 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.val)
        );
        // init
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.offer(list);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode temp = priorityQueue.poll();
            cur.next = temp;
            cur = cur.next;
            if (temp.next != null) {
                priorityQueue.offer(temp.next);
            }
        }
        return dummy.next;
    }

    // 还可以采用归并排序的思想，两两合并
    public ListNode mergeKLists2(ListNode[] lists) {
        // 迭代进行合并
        /*
        int n = lists.length;
        if (n == 0) {
            return null;
        }
        for (int subLength = 1; subLength < n; subLength <<= 1) {
            // 相邻两两合并
            for (int i = 0; i < n - subLength; i += subLength << 1) {
                lists[i] = mergeTwoLists(lists[i], lists[i + subLength]);
            }
        }
        return lists[0];
        */

        // 递归进行合并
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (r - l) / 2 + l;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        cur.next = (a != null ? a : b);
        return dummy.next;
    }
}
