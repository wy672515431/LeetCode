package LeetCode;

public class MyLinkedList {
    //记录链表的长度
    private int length;
    private ListNode dummy;

    public MyLinkedList() {
        dummy = new ListNode();
        length = 0;
    }

    public int get(int index) {
        ListNode head = dummy.next;
        if (index < 0 || index >= length) {
            return -1;
        }
        while (index > 0) {
            head = head.next;
            index--;
        }
        return head.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = dummy.next;
        dummy.next = newNode;
        length++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode head = dummy;
        while (head.next != null) {
            head = head.next;
        }
        head.next = newNode;
        length++;
    }

    public void addAtIndex(int index, int val) {
        ListNode newNode = new ListNode(val);
        if (index < 0) {
            newNode.next = dummy.next;
            dummy.next = newNode;
            length++;
        } else if (index <= length) {
            ListNode firstNode = dummy;
            ListNode head = dummy.next;
            while (index > 0) {
                firstNode = firstNode.next;
                head = head.next;
                index--;
            }
            newNode.next = firstNode.next;
            firstNode.next = newNode;
            length++;
        } else {
            //do nothing
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }
        ListNode firstNode = dummy;
        ListNode head = dummy.next;
        while (index > 0) {
            firstNode = firstNode.next;
            head = head.next;
            index--;
        }
        firstNode.next = head.next;
        length--;
    }


    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(7);
        list.addAtHead(2);
        list.addAtHead(1);
        list.addAtIndex(3, 0);
        list.deleteAtIndex(2);
        list.addAtHead(6);
        list.addAtTail(4);
        list.get(4);
        list.addAtHead(4);
        list.addAtIndex(5, 0);
        list.addAtHead(6);
    }

}
