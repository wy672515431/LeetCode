package bytedance.线段树;

public class Range模块 {
}

class RangeModule {
    private static final int N = (int) 1e9;
    private SegmentTree segmentTree;
    public RangeModule() {
        this.segmentTree = new SegmentTree(N);
    }

    public void addRange(int left, int right) {
        segmentTree.update(left, right - 1, true);
    }

    public boolean queryRange(int left, int right) {
        return segmentTree.query(left, right - 1);
    }

    public void removeRange(int left, int right) {
        segmentTree.update(left, right - 1, false);
    }

    static class SegmentTree {
        // 动态开点
        private static class Node {
            boolean lazy, val, flag;
            Node left, right;
            public Node() {}
        }
        int n;
        Node root;
        public SegmentTree(int n) {
            this.n = n;
            root = new Node();
        }

        public void update(int left, int right, boolean x) {
            internalUpdate(left, right, 1, N, root, x);
        }

        private void internalUpdate(int left, int right, int start, int end, Node cur, boolean x) {
            if (left <= start && right >= end) {
                cur.val = x;
                if (start != end) {
                    // 表示有子节点需要更新
                    cur.lazy = x;
                    cur.flag = true;
                }
                return;
            }
            // 添加子节点
            addNode(cur);
            int mid = start + (end - start) / 2;
            // 传递懒标记
            if (cur.flag) {
                pushDown(cur);
            }
            if (left <= mid) {
                internalUpdate(left, right, start, mid, cur.left, x);
            }
            if (right > mid) {
                internalUpdate(left, right, mid + 1, end, cur.right, x);
            }
            // 更新当前节点
            pushUp(cur);
        }

        public boolean query(int left, int right) {
            return internalQuery(left, right, 1, N, root);
        }

        private boolean internalQuery(int left, int right, int start, int end, Node cur) {
            if (left <= start && right >= end) {
                return cur.val;
            }
            int mid = start + (end - start) / 2;
            addNode(cur);
            if (cur.flag) {
                pushDown(cur);
            }
            boolean res = true;
            if (left <= mid) {
                res = internalQuery(left, right, start, mid, cur.left);
            }
            if (right > mid) {
                res = res && internalQuery(left, right, mid + 1, end, cur.right);
            }
            return res;
        }

        private void pushUp(Node cur) {
            cur.val = cur.left.val && cur.right.val;
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
            cur.left.val = cur.lazy;
            cur.left.lazy = cur.lazy;
            cur.left.flag = cur.flag;
            cur.right.val = cur.lazy;
            cur.right.lazy = cur.lazy;
            cur.right.flag = cur.flag;
            cur.flag = false;
        }
    }
}
