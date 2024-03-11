package LeetCode_321;

import LeetCode.ListNode;

import java.util.Stack;

public class C {
    public ListNode removeNodes(ListNode head) {
        ListNode dummyNode = new ListNode(-1, head);
        Stack<ListNode> stack = new Stack<>();
        ListNode curNode = head;
        while (curNode != null) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.peek().val < curNode.val) {
                    ListNode tempNode = stack.pop();
                    if (stack.isEmpty()) {
                        dummyNode.next = curNode;
                    } else {
                        stack.peek().next = curNode;
                    }
                }
            }
            stack.push(curNode);
            curNode = curNode.next;
        }
        return dummyNode.next;
    }
}
