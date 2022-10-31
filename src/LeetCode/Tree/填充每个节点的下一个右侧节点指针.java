package LeetCode.Tree;

public class 填充每个节点的下一个右侧节点指针 {
    //我们存在两种情况，若两个节点是同一个父节点，则直接可以连接
    //node.left.next = node.right
    //如果不是，我们可以通过父节点之间的连接进行连接
    //node.right.next = node.next.left;
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node leftmost = root;
        //当leftmost.left为null时，则到达最下层,在第n层建立第n+1层
        while (leftmost.left != null) {
            //遍历
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        //此时leftmost应该是每层的最左节点
        Node leftmost = root;
        while (leftmost != null) {
            //遍历
            Node head = leftmost;
            //被连接的前一个节点，被连接的后一个节点
            Node leftNode = null;
            Node rightNode = null;
            leftmost = null;
            while (head != null) {
                if (head.left != null) {
                    if (leftNode == null) {
                        leftNode = head.left;
                        leftmost = leftNode;
                    } else {
                        rightNode = head.left;
                    }
                }
                if (leftNode != null && rightNode != null) {
                    leftNode.next = rightNode;
                    leftNode = rightNode;
                    rightNode = null;
                }
                if (head.right != null) {
                    if (leftNode == null) {
                        leftNode = head.right;
                        leftmost = leftNode;
                    } else {
                        rightNode = head.right;
                    }
                }
                if (leftNode != null && rightNode != null) {
                    leftNode.next = rightNode;
                    leftNode = rightNode;
                    rightNode = null;
                }
                head = head.next;
            }
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
