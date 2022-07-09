package 数据结构学习计划;

import java.util.List;

public class 扁平化多级双向链表 {
    /**
     * 针对一个节点我们进行分析
     * node.child存在
     * node.next = node.child
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }


    public Node dfs(Node node) {
        Node cur = node;
        //记录链表的最后一个节点
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;

                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }
}


class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
