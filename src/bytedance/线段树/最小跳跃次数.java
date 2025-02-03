package bytedance.线段树;

import java.util.Arrays;

public class 最小跳跃次数 {
    public static void main(String[] args) {
        最小跳跃次数 s = new 最小跳跃次数();
        int[] jump = {3, 7, 6, 1, 4, 3, 7, 8, 1, 2, 8, 5, 9, 8, 3, 2, 7, 5, 1, 1};
        System.out.println(s.minJump(jump));
    }

    public int minJump(int[] jump) {
        int n = jump.length;
        SegmentTree sg = new SegmentTree(n);
        sg.update(0, 0, 0);
        for (int i = 0; i < n; i++) {
            int dist = i + jump[i];
            int cnt = sg.query(i);
//            System.out.println("i: " + i + " dist: " + dist + " cnt: " + cnt);
            sg.update(Math.min(dist, n), Math.min(dist, n), cnt + 1);
            sg.update(0, Math.min(dist, n) - 1, cnt + 2);
//            for (int j = 0; j <= Math.min(dist, n); j++) {
//                System.out.println("j: " + j + " query: " + sg.query(j, j));
//            }
//            System.out.println("--------------------");
        }
        return sg.query(n);
    }

    static class SegmentTree {
        int[] tree;
        int[] lazy;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            Arrays.fill(tree, Integer.MAX_VALUE);
        }

        public int query(int l) {
            return query(l, 0, n, 1);
        }

        private int query(int l, int s, int e, int treeIndex) {
            if (s == e) {
                return tree[treeIndex];
            }
            int mid = s + (e - s) / 2;
            if (lazy[treeIndex] != 0) {
                pushDown(treeIndex);
            }
            if (l <= mid) {
                return query(l, s, mid, treeIndex << 1);
            } else {
                return query(l, mid + 1, e, treeIndex << 1 | 1);
            }
        }

        public void update(int l, int r, int val) {
            update(l, r, 0, n, val, 1);
        }

        private void update(int l, int r, int s, int e, int val, int treeIndex) {
            if (l <= s && r >= e) {
                tree[treeIndex] = Math.min(tree[treeIndex], val);
                if (s != e) {
                    lazy[treeIndex] = lazy[treeIndex] == 0 ? val : Math.min(lazy[treeIndex], val);
                }
                return;
            }
            int mid = s + (e - s) / 2;
            if (lazy[treeIndex] != 0) {
                pushDown(treeIndex);
            }
            if (l <= mid) {
                update(l, r, s, mid, val, treeIndex << 1);
            }
            if (r > mid) {
                update(l, r, mid + 1, e, val, treeIndex << 1 | 1);
            }
            pushUp(treeIndex);
        }

        private void pushDown(int treeIndex) {
            tree[treeIndex << 1] = Math.min(tree[treeIndex << 1], lazy[treeIndex]);
            lazy[treeIndex << 1] = lazy[treeIndex << 1] == 0 ? lazy[treeIndex] : Math.min(lazy[treeIndex << 1], lazy[treeIndex]);
            tree[treeIndex << 1 | 1] = Math.min(tree[treeIndex << 1 | 1], lazy[treeIndex]);
            lazy[treeIndex << 1 | 1] = lazy[treeIndex << 1 | 1] == 0 ? lazy[treeIndex] : Math.min(lazy[treeIndex << 1 | 1], lazy[treeIndex]);
            lazy[treeIndex] = 0;
        }

        private void pushUp(int treeIndex) {
            tree[treeIndex] = Math.min(tree[treeIndex << 1], tree[treeIndex << 1 | 1]);
        }
    }
}
