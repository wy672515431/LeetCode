package bytedance.线段树;

public class 我的日程安排III {
}

class MyCalendarThree {
    private static final int N = (int) 1e9;
    private final SegmentTree segmentTree;
    public MyCalendarThree() {
        segmentTree = new SegmentTree(N);
    }

    public int book(int startTime, int endTime) {
        segmentTree.add(startTime, endTime - 1, 1);
        return segmentTree.query(0, N);
    }

    static class SegmentTree {
        private static class Node {
            int val;
            int lazy;
            Node left, right;
            Node() {}
        }

        Node root;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            root = new Node();
        }

        public int query(int l, int r) {
            return query(l, r, 0, n, root);
        }

        private int query(int l, int r, int start, int end, Node cur) {
            if (l <= start && r >= end) {
                return cur.val;
            }
            addNode(cur);
            if (cur.lazy != 0) {
                pushDown(cur);
            }
            int mid = (end - start) / 2 + start;
            int res = Integer.MIN_VALUE;
            if (l <= mid) {
                res = Math.max(res, query(l, r, start, mid, cur.left));
            }
            if (r > mid) {
                res = Math.max(res, query(l, r, mid + 1, end, cur.right));
            }
            return res;
        }

        public void add(int l, int r, int val) {
            add(l, r, 0, n, val, root);
        }

        private void add(int l, int r, int start, int end, int val, Node cur) {
            if (l <= start && r >= end) {
                cur.val += val;
                if (start != end) {
                    cur.lazy += val;
                }
                return;
            }
            addNode(cur);
            if (cur.lazy != 0) {
                pushDown(cur);
            }
            int mid = (end - start) / 2 + start;
            if (l <= mid) {
                add(l, r, start, mid, val, cur.left);
            }
            if (r > mid) {
                add(l, r, mid + 1, end, val, cur.right);
            }
            pushUp(cur);
        }

        private void addNode(Node cur) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
        }

        private void pushDown(Node cur) {
            cur.left.val += cur.lazy;
            cur.left.lazy += cur.lazy;
            cur.right.val += cur.lazy;
            cur.right.lazy += cur.lazy;
            cur.lazy = 0;
        }

        private void pushUp(Node cur) {
            cur.val = Math.max(cur.left.val, cur.right.val);
        }
    }
}
