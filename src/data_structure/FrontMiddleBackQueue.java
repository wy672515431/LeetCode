package data_structure;

import java.util.HashMap;

class FrontMiddleBackQueue {
    // 伪节点
    Node dummyNode;
    // 中间节点
    Node middleNode;
    // 队列长度
    int len;
    public FrontMiddleBackQueue() {
        dummyNode = new Node(-1);
        // next指向头节点
        dummyNode.next = dummyNode;
        // prev指向尾节点
        dummyNode.prev = dummyNode;
        middleNode = dummyNode;
        len = 0;
    }

    public void pushFront(int val) {
        Node curNode = new Node(val);

        // 插入节点
        dummyNode.next.prev = curNode;
        curNode.next = dummyNode.next;
        dummyNode.next = curNode;
        curNode.prev = dummyNode;
        // 长度加1,如果长度为奇数，则中点往后移
        len++;
        if (len % 2 == 0) {
            middleNode = middleNode.prev;
        } else if (len == 1) {
            middleNode = middleNode.next;
        }
    }

    public void pushMiddle(int val) {
        // 长度为奇数往前添加，长度为偶数往后添加
        Node curNode = new Node(val);
        len++;
        if (len % 2 == 0) {
            middleNode.prev.next = curNode;
            curNode.prev = middleNode.prev;
            middleNode.prev = curNode;
            curNode.next = middleNode;
        } else {
            middleNode.next.prev = curNode;
            curNode.next = middleNode.next;
            middleNode.next = curNode;
            curNode.prev = middleNode;
        }
        middleNode = curNode;
    }

    public void pushBack(int val) {
        Node curNode = new Node(val);
        dummyNode.prev.next = curNode;
        curNode.prev = dummyNode.prev;
        dummyNode.prev = curNode;
        curNode.next = dummyNode;
        // 长度加1,如果长度为奇数，则中点往后移
        len++;
        if (len % 2 == 1) {
            middleNode = middleNode.next;
        }
    }

    public int popFront() {
        int val = dummyNode.next.val;
        dummyNode.next.next.prev = dummyNode;
        dummyNode.next = dummyNode.next.next;
        // 长度减1，如果长度为偶数
        len = Math.max(len - 1, 0);
        if (len % 2 == 1) {
            middleNode = middleNode.next;
        } else if (len == 0) {
            middleNode = middleNode.prev;
        }
        return val;
    }

    public int popMiddle() {
        int val = middleNode.val;
        middleNode.prev.next = middleNode.next;
        middleNode.next.prev = middleNode.prev;
        len = Math.max(len - 1, 0);
        if (len % 2 == 0) {
            middleNode = middleNode.prev;
        } else {
            middleNode = middleNode.next;
        }
        return val;
    }

    public int popBack() {
        int val = dummyNode.prev.val;
        dummyNode.prev.prev.next = dummyNode;
        dummyNode.prev = dummyNode.prev.prev;
        len = Math.max(len - 1, 0);
        if (len % 2 == 0) {
            middleNode = middleNode.prev;
        }
        return val;
    }
}

class Node {
    Node next;
    Node prev;
    int val;
    public Node() {}
    public Node(int val) {
        this.val = val;
    }
}

/*
class FrontMiddleBackQueue {
    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<Integer>();
        right = new ArrayDeque<Integer>();
    }

    public void pushFront(int val) {
        left.offerFirst(val);
        if (left.size() == right.size() + 2) {
            right.offerFirst(left.pollLast());
        }
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size() + 1) {
            right.offerFirst(left.pollLast());
        }
        left.offerLast(val);
    }

    public void pushBack(int val) {
        right.offerLast(val);
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
    }

    public int popFront() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = left.pollFirst();
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
        return val;
    }

    public int popMiddle() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = left.pollLast();
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
        return val;
    }

    public int popBack() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = 0;
        if (right.isEmpty()) {
            val = left.pollLast();
        } else {
            val = right.pollLast();
            if (left.size() == right.size() + 2) {
                right.offerFirst(left.pollLast());
            }
        }
        return val;
    }
}
 */
