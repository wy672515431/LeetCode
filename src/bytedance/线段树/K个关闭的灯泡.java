package bytedance.线段树;

/**
 * 我做成了最少有k个灯泡
 */
public class K个关闭的灯泡 {
    public static void main(String[] args) {
        K个关闭的灯泡 main = new K个关闭的灯泡();
        System.out.println(main.kEmptySlots(new int[]{1, 2, 3, 4, 5}, 1));
    }

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        SegmentTree segmentTree = new SegmentTree(n, k);
        for (int i = 0; i < n; i++) {
            segmentTree.update(bulbs[i] - 1);
            if (segmentTree.query(0, n - 1)) {
                return i + 1;
            }
        }
        return -1;
    }

    static class SegmentTree {
        private static class Node {
            int left;
            int right;
            int interval;
            boolean exactK;
            Node() {
                this.left = -1;
                this.right = -1;
                this.interval = -1;
                this.exactK = false;
            }
        }

        Node[] tree;
        int n;
        int k;

        public SegmentTree(int n, int k) {
            this.n = n;
            this.k = k;
            tree = new Node[n * 4];
            for (int i = 1; i < n * 4; i++) {
                tree[i] = new Node();
            }
        }

        public void update(int index) {
            update(index, 0, n - 1, 1);
        }

        private void update(int index, int start, int end, int treeIndex) {
            if (start == end) {
                tree[treeIndex].left = index;
                tree[treeIndex].right = index;
                tree[treeIndex].interval = -1;
                tree[treeIndex].exactK = false;
                return;
            }
            int mid = (end - start) / 2 + start;
            if (index <= mid) {
                update(index, start, mid, treeIndex * 2);
            } else {
                update(index, mid + 1, end, treeIndex * 2 + 1);
            }
            pushUp(treeIndex);
        }

        public boolean query(int l, int r) {
            return query(l, r, 0, n - 1, 1);
        }

        private boolean query(int l, int r, int start, int end, int treeIndex) {
            if (l <= start && r >= end) {
                return tree[treeIndex].exactK;
            }
            int mid = (end - start) / 2 + start;
            boolean res = false;
            if (l <= mid) {
                res |= query(l, r, start, mid, treeIndex * 2);
            }
            if (r > mid) {
                res |= query(l, r, mid + 1, end, treeIndex * 2 + 1);
            }
            return res;
        }

        private void pushUp(int treeIndex) {
            Node left = tree[treeIndex * 2];
            Node right = tree[treeIndex * 2 + 1];
            int lmax = Math.max(left.left, left.right);
            int rmax = Math.max(right.left, right.right);
            int lmin = Math.min(left.left, left.right);
            int rmin = Math.min(right.left, right.right);
            if (lmax == -1 && rmax == -1) {
                // do nothing
            } else if (lmax == -1) {
                if (rmin == -1) {
                    tree[treeIndex].left = rmax;
                } else {
                    tree[treeIndex].left = rmin;
                }
                tree[treeIndex].right = rmax;
                tree[treeIndex].interval = right.interval;
                tree[treeIndex].exactK = right.exactK;
            } else if (rmax == -1) {
                if (lmin == -1) {
                    tree[treeIndex].left = lmax;
                } else {
                    tree[treeIndex].left = lmin;
                }
                tree[treeIndex].right = lmax;
                tree[treeIndex].interval = left.interval;
                tree[treeIndex].exactK = left.exactK;
            } else {
                if (lmin == -1) {
                    tree[treeIndex].left = lmax;
                } else {
                    tree[treeIndex].left = lmin;
                }
                tree[treeIndex].right = rmax;
                tree[treeIndex].interval = Math.max(left.interval, right.interval);
                tree[treeIndex].exactK = left.exactK || right.exactK;
                if (rmin != -1) {
                    tree[treeIndex].interval = Math.max(tree[treeIndex].interval, rmin - lmax - 1);
                    if (rmin - lmax - 1 == k) {
                        tree[treeIndex].exactK = true;
                    }
                }
            }
        }
    }
}
