package bytedance.线段树;

public class 找到Alice和Bob可以相遇的建筑 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        int[] ans = new int[m];
        SegmentTree sg = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            sg.update(i, heights[i]);
        }
        for (int i = 0; i < m; i++) {
            // 保证 a <= b
            int a = Math.min(queries[i][0], queries[i][1]);
            int b = Math.max(queries[i][0], queries[i][1]);
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
                continue;
            }
            // 找到下标k, 满足k > b且heights[k] > heights[a]
            ans[i] = sg.query(b + 1, heights[a]);
        }
        return ans;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        public int query(int i, int height) {
            return query(i, height, 0, n - 1, 1);
        }

        private int query(int i, int height, int s, int e, int treeIndex) {
            if (tree[treeIndex] <= height) {
                return -1;
            }

            if (s == e) {
                return s;
            }

            int mid = s + (e - s) / 2;
            if (i <= mid) {
                int res = query(i, height, s, mid, 2 * treeIndex);
                // 存在
                if (res != -1) {
                    return res;
                }
            }
            return query(i, height, mid + 1, e, 2 * treeIndex + 1);
        }

        public void update(int i, int val) {
            update(i, val, 0, n - 1, 1);
        }

        private void update(int i, int val, int s, int e, int treeIndex) {
            if (s == e) {
                tree[treeIndex] = val;
                return;
            }
            int mid = s + (e - s) / 2;
            if (i <= mid) {
                update(i, val, s, mid, 2 * treeIndex);
            } else {
                update(i, val, mid + 1, e, 2 * treeIndex + 1);
            }
            pushUp(treeIndex);
        }

        private void pushUp(int treeIndex) {
            tree[treeIndex] = Math.max(tree[2 * treeIndex], tree[2 * treeIndex + 1]);
        }
    }
}
