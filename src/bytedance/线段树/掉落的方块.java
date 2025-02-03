package bytedance.线段树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 掉落的方块 {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        // 离散化
        int n = positions.length;
        int[] arr = new int[2 * n];
        int rightMost = 0;
        for (int i = 0; i < n; i++) {
            arr[2 * i] = positions[i][0];
            arr[2 * i + 1] = positions[i][0] + positions[i][1];
            rightMost = Math.max(rightMost, arr[2 * i + 1]);
        }
        Map<Integer, Integer> map = discrete(arr);
        SegmentTree st = new SegmentTree(map.get(rightMost));
        int max = 0;
        for (int i = 0; i < n; i++) {
            int left = positions[i][0], right = positions[i][0] + positions[i][1];
            int l = map.get(left), r = map.get(right);
            // 查询
            int height = st.query(l, r - 1);
            max = Math.max(max, height + positions[i][1]);
            st.update(l, r - 1, height + positions[i][1]);
            ans.add(max);
        }
        return ans;
    }

    private Map<Integer, Integer> discrete(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int index = 0;
        for (int num : list) {
            map.put(num, index++);
        }
        return map;
    }

    static class SegmentTree {
        private final int[] tree;
        private final int[] lazy;
        private int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            build(0, n - 1, 1);
        }

        private void build(int start, int end, int treeIndex) {
            if (start == end) {
                // 初始化为0
                tree[treeIndex] = 0;
                return;
            }
            int mid = start + (end - start) / 2;
            build(start, mid, treeIndex * 2);
            build(mid + 1, end, treeIndex * 2 + 1);
            pushUp(treeIndex);
        }

        private void pushUp(int treeIndex) {
            tree[treeIndex] = Math.max(tree[treeIndex * 2], tree[treeIndex * 2 + 1]);
        }

        public void update(int left, int right, int  val) {
            internalUpdate(left, right, val, 0, n - 1, 1);
        }

        private void internalUpdate(int left, int right, int val, int start, int end, int treeIndex) {
            if (left <= start && end <= right) {
                tree[treeIndex] = val;
                if (start != end) {
                    lazy[treeIndex] = val;
                }
                return;
            }
            int mid = start + (end - start) / 2;
            if (lazy[treeIndex] != 0) {
                pushDown(start, mid, end, treeIndex);
            }
            if (left <= mid) {
                internalUpdate(left, right, val, start, mid, treeIndex * 2);
            }

            if (right > mid) {
                internalUpdate(left, right, val, mid + 1, end, treeIndex * 2 + 1);
            }

            pushUp(treeIndex);
        }

        public int query(int left, int right) {
            return internalQuery(left, right, 0, n - 1, 1);
        }

        private int internalQuery(int left, int right, int start, int end, int treeIndex) {
            if (left <= start && end <= right) {
                return tree[treeIndex];
            }
            int mid = start + (end - start) / 2;
            int ans = 0;
            if (lazy[treeIndex] != 0) {
                pushDown(start, mid, end, treeIndex);
            }
            if (left <= mid) {
                ans = Math.max(ans, internalQuery(left, right, start, mid, treeIndex * 2));
            }
            if (right > mid) {
                ans = Math.max(ans, internalQuery(left, right, mid + 1, end, treeIndex * 2 + 1));
            }
            return ans;
        }

        private void pushDown(int start, int mid, int end, int treeIndex) {
            tree[treeIndex * 2] = tree[treeIndex];
            lazy[treeIndex * 2] = lazy[treeIndex];
            tree[treeIndex * 2 + 1] = tree[treeIndex];
            lazy[treeIndex * 2 + 1] = lazy[treeIndex];
            lazy[treeIndex] = 0;
        }
    }


}


