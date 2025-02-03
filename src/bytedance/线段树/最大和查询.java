package bytedance.线段树;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 最大和查询 {
    public static void main(String[] args) {
        最大和查询 s = new 最大和查询();
        int[] nums1 = {4, 3, 1 ,2};
        int[] nums2 = {2, 4, 9, 5};
        int[][] queries = {{4, 1}, {1, 3}, {2, 5}};
        int[] ans = s.maximumSumQueries(nums1, nums2, queries);
        System.out.println(Arrays.toString(ans));
    }
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        Node[] pairs = new Node[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Node(nums1[i], nums2[i], nums1[i] + nums2[i]);
        }

        Arrays.sort(pairs, (a, b) -> {
            if (a.first == b.first) {
                return a.second - b.second;
            }
            return a.first - b.first;
        });

        Deque<Node> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // filter
            while (!deque.isEmpty() && deque.peekLast().second < pairs[i].second) {
                deque.pollLast();
            }
            deque.offerLast(pairs[i]);
        }

        pairs = deque.stream().toList().toArray(new Node[0]);
        n = pairs.length;
        SegmentTree sg = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            sg.update(i, pairs[i].sum);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            // 二分
            // first 递增, second 递减
            int low = 0, high = n - 1;
            if (pairs[high].first < x || pairs[low].second < y) {
                ans[i] = -1;
                continue;
            }
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (pairs[mid].first < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            // 第一个大于等于x的下标;
            int l = low;
            low = 0;
            high = n - 1;
            // 找到第一个小于y的下标
            int r = 0;
            if (pairs[high].second >= y) {
                r = high;
            } else {
                while (low < high) {
                    int mid = (high - low) / 2 + low;
                    if (pairs[mid].second >= y) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                r = low - 1;
            }
            if (l > r) {
                ans[i] = -1;
            } else {
                ans[i] = sg.query(l, r);
            }
        }
        return ans;
    }

    static class Node {
        int first;
        int second;
        int sum;

        public Node(int first, int second, int sum) {
            this.first = first;
            this.second = second;
            this.sum = sum;
        }

        public Node() {
            this(-1, -1, -1);
        }
    }

    static class SegmentTree {

        int n;
        int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            this.tree = new int[4 * n];
        }

        public int query(int l, int r) {
            return query(l, r, 0, n - 1, 1);
        }

        private int query(int l, int r, int s, int e, int treeIndex) {
            if (l <= s && r >= e) {
                return tree[treeIndex];
            }
            int mid = (e - s) / 2 + s;
            int res = Integer.MIN_VALUE;
            if (l <= mid) {
                res = Math.max(res, query(l, r, s, mid, treeIndex * 2));
            }
            if (r > mid) {
                res = Math.max(res, query(l, r, mid + 1, e, treeIndex * 2 + 1));
            }
            return res;
        }

        public void update(int i, int val) {
            update(i, val, 0, n - 1, 1);
        }

        private void update(int i, int val, int s, int e, int treeIndex) {
            if (s == e) {
                tree[treeIndex] = val;
                return;
            }
            int mid = (e - s) / 2 + s;
            if (i <= mid) {
                update(i, val, s, mid, treeIndex * 2);
            } else {
                update(i, val, mid + 1, e, treeIndex * 2 + 1);
            }
            pushUp(treeIndex);
        }

        private void pushUp(int i) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }
    }
}
