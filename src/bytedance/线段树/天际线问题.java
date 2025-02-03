package bytedance.线段树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 天际线问题 {
    private static final int N = Integer.MAX_VALUE;
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        List<List<Integer>> res = new ArrayList<>();
        SegmentTree segmentTree = new SegmentTree(N);
        // 按照左端点和高度排序
        Arrays.sort(buildings, Comparator.comparingInt(a -> a[2]));
        for (int[] building: buildings) {
            // 右端点要减1，因为两个建筑不相邻时，必须返回第一个地面的点
            segmentTree.update(building[0], building[1] - 1, building[2]);
        }
        int[] positions = new int[2 * n];
        for (int i = 0; i < n; i++) {
            positions[i * 2] = buildings[i][0];
            positions[i * 2 + 1] = buildings[i][1];
        }
        Arrays.sort(positions);
        long lastHeight = -1;
        for (int i = 0; i < 2 * n; i++) {
            int pos = positions[i];
            long height = segmentTree.query(pos);
            if (height != lastHeight) {
                List<Integer> list = new ArrayList<>();
                list.add(pos);
                list.add((int)height);
                res.add(list);
                lastHeight = height;
            }
        }
        return res;
    }

    static class SegmentTree {
        private static class Node {
            long lazy, val;
            Node left, right;

            Node() {
            }
        }

        int n;
        Node root;

        public SegmentTree(int n) {
            this.n = n;
            root = new Node();
        }

        public void update(int l, int r, int val) {
            internalUpdate(l, r, 0, n, val, root);
        }

        private void internalUpdate(int l, int r, int start, int end, int val, Node cur) {
            if (l <= start && r >= end) {
                cur.val = (long) (end - start + 1) * val;
                if (start != end) {
                    cur.lazy = val;
                }
                return;
            }
            addNode(cur);
            int mid = (end - start) / 2 + start;
            if (cur.lazy != 0) {
                pushDown(start, mid, end, cur);
            }
            if (l <= mid) {
                internalUpdate(l, r, start, mid, val, cur.left);
            }
            if (r > mid) {
                internalUpdate(l, r, mid + 1, end, val, cur.right);
            }
            pushUp(cur);
        }

        public long query(int index) {
            return internalQuery(index, 0, n, root);
        }

        private long internalQuery(int index, int start, int end, Node cur) {
            if (start == end) {
                return cur.val;
            }
            addNode(cur);
            int mid = (end - start) / 2 + start;
            if (cur.lazy != 0) {
                pushDown(start, mid, end, cur);
            }
            if (index <= mid) {
                return internalQuery(index, start, mid, cur.left);
            } else {
                return internalQuery(index, mid + 1, end, cur.right);
            }
        }

        private void addNode(Node cur) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
        }


        private void pushDown(int left, int mid, int right, Node cur) {
            cur.left.val = (mid - left + 1) * cur.lazy;
            cur.left.lazy = cur.lazy;
            cur.right.val = (right - mid) * cur.lazy;
            cur.right.lazy = cur.lazy;
            cur.lazy = 0;
        }

        private void pushUp(Node cur) {
            cur.val = cur.left.val + cur.right.val;
        }
    }
}
