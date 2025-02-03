package bytedance.线段树;

public class 统计区间中的整数数目 {
}

class CountIntervals {
    private static final int N = (int) 1e9;
    private SegmentTree sg;
    public CountIntervals() {
        sg = new SegmentTree(N);
    }

    public void add(int left, int right) {
        sg.update(left, right, 1);
    }

    public int count() {
        return sg.query(1, N);
    }

    static class SegmentTree {
        static class Node {
            int val;
            int lazy;
            Node left, right;

            Node() {
            }
        }

        Node root;
        int n;

        SegmentTree(int n) {
            this.n = n;
            root = new Node();
        }

        public void update(int l, int r, int val) {
            update(l, r, 1, n, val, root);
        }

        private void update(int l, int r, int s, int e, int val, Node cur) {
            if (l <= s && r >= e) {
                cur.val = (e - s + 1) * val;
                if (s != e) {
                    cur.lazy = val;
                }
                return;
            }
            addNode(cur);
            int m = (e - s) / 2 + s;
            if (cur.lazy != 0) {
                pushDown(cur, s, m, e);
            }
            if (l <= m) {
                update(l, r, s, m, val, cur.left);
            }
            if (r > m) {
                update(l, r, m + 1, e, val, cur.right);
            }
            pushUp(cur);
        }

        public int query(int l, int r) {
            return query(l, r, 1, n, root);
        }

        private int query(int l, int r, int s, int e, Node cur) {
            if (l <= s && r >= e) {
                return cur.val;
            }
            addNode(cur);
            int m = (e - s) / 2 + s;
            if (cur.lazy != 0) {
                pushDown(cur, s, m, e);
            }
            int res = 0;
            if (l <= m) {
                res += query(l, r, s, m, cur.left);
            }
            if (r > m) {
                res += query(l, r, m + 1, e, cur.right);
            }
            return res;
        }

        private void addNode(Node cur) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
        }

        private void pushDown(Node cur, int s, int m, int e) {
            cur.left.val = (m - s + 1) * cur.lazy;
            cur.left.lazy = cur.lazy;
            cur.right.val = (e - m) * cur.lazy;
            cur.right.lazy = cur.lazy;
            cur.lazy = 0;
        }

        private void pushUp(Node cur) {
            cur.val = cur.left.val + cur.right.val;
        }
    }
}
