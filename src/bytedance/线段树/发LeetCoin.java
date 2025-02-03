package bytedance.线段树;

import java.util.ArrayList;
import java.util.List;

public class 发LeetCoin {
    // 构建树的DFS序，为节点编号，并且统计节点子树大小
    static final int MOD = (int) 1e9 + 7;
    List<ArrayList<Integer>> graph = new ArrayList<>();
    int[] ids;
    int[] size;
    // 编号
    int cnt = 0;

    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        this.ids = new int[n + 1];
        this.size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] rel : leadership) {
            graph.get(rel[0]).add(rel[1]);
        }
        dfsOrder(1);
        SegmentTree tree = new SegmentTree(n + 1);
        List<Integer> res = new ArrayList<>();
        for (int[] op : operations) {
            if (op[0] == 1) {
                tree.update(ids[op[1]], ids[op[1]], op[2]);
            } else if (op[0] == 2) {
                tree.update(ids[op[1]], ids[op[1]] + size[op[1]] - 1, op[2]);
            } else {
                int ans = (int)(tree.query(ids[op[1]], ids[op[1]] + size[op[1]] - 1) % MOD);
                res.add(ans);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private int dfsOrder(int u) {
        ids[u] = ++cnt;
        for (int v : graph.get(u)) {
            size[u] += dfsOrder(v);
        }
        return ++size[u];
    }

    static class SegmentTree {
        int n;
        long[] tree;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            this.tree = new long[n << 2];
            this.lazy = new long[n << 2];
        }

        private long query(int l, int r, int s, int e, int treeIndex) {
            if (l <= s && r >= e) {
                return tree[treeIndex];
            }
            int mid = (e - s) / 2 + s;
            if (lazy[treeIndex] != 0) {
                pushDown(treeIndex, s, mid, e);
            }
            long sum = 0L;
            if (l <= mid) {
                sum += query(l, r, s, mid, treeIndex << 1);
            }
            if (r > mid) {
                sum += query(l, r, mid + 1, e, treeIndex << 1 | 1);
            }
            return sum;
        }

        public long query(int l, int r) {
            return query(l, r, 1, n, 1);
        }

        public void update(int l, int r, int val) {
            update(l, r, val, 1, n, 1);
        }

        private void update(int l, int r, int val, int s, int e, int treeIndex) {
            if (l <= s && r >= e) {
                tree[treeIndex] += ((long)(e - s + 1) * val);
                if (s != e) {
                    lazy[treeIndex] += val;
                }
                return;
            }
            int mid = (e - s) / 2 + s;
            if (lazy[treeIndex] != 0) {
                pushDown(treeIndex, s, mid, e);
            }
            if (l <= mid) {
                update(l, r, val, s, mid, treeIndex << 1);
            }
            if (r > mid) {
                update(l, r, val, mid + 1, e, treeIndex << 1 | 1);
            }
            pushUp(treeIndex);
        }

        private void pushDown(int treeIndex, int s, int mid, int e) {
            tree[treeIndex << 1] += ((mid - s + 1) * lazy[treeIndex]);
            lazy[treeIndex << 1] += lazy[treeIndex];
            tree[treeIndex << 1 | 1] += ((e - mid) * lazy[treeIndex]);
            lazy[treeIndex << 1 | 1] += lazy[treeIndex];
            lazy[treeIndex] = 0;
        }

        private void pushUp(int treeIndex) {
            tree[treeIndex] = tree[treeIndex << 1] + tree[treeIndex << 1 | 1];
        }
    }
}
