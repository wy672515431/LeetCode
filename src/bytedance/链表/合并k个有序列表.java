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
}
