package bytedance.线段树;

public class 满足不等式的数对数目 {
    public static void main(String[] args) {
        满足不等式的数对数目 s = new 满足不等式的数对数目();
        int[] nums1 = {3, -1};
        int[] nums2 = {-2, 2};
        int diff = 1;
        System.out.println(s.numberOfPairs(nums1, nums2, diff));
    }
    private static final int N = 400010;
    private static final int ADD_NUM = 200000;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        // (nums1[i] - nums2[i]) <= nums1[j] - nums2[j] + diff
        int n = nums1.length;
        SegmentTree sg = new SegmentTree(N);
        for (int i = 0; i < n; i++) {
            sg.add(nums1[i] - nums2[i] + ADD_NUM, 1);
        }
        long ans = 0L;
        for (int i = n - 1; i >= 0; i--) {
            int val = nums1[i] - nums2[i] + ADD_NUM;
            sg.add(val, -1);
            ans += sg.query(0, Math.min(val + diff, N -1));
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

        public int query(int l, int r) {
            return query(l, r, 0, n - 1, 1);
        }

        private int query(int l, int r, int s, int e, int treeIndex) {
            if (l <= s && r >= e) {
                return tree[treeIndex];
            }
            int mid = (e - s) / 2 + s;
            int sum = 0;
            if (l <= mid) {
                sum += query(l, r, s, mid, treeIndex << 1);
            }
            if (r > mid) {
                sum += query(l, r, mid + 1, e, treeIndex << 1 | 1);
            }
            return sum;
        }

        public void add(int i, int val) {
            add(i, val, 0, n - 1, 1);
        }

        private void add(int i, int val, int s, int e, int treeIndex) {
            if (s == e) {
                tree[treeIndex] += val;
                return;
            }
            int mid = (e - s) / 2 + s;
            if (i <= mid) {
                add(i, val, s, mid, treeIndex << 1);
            } else {
                add(i, val, mid + 1, e, treeIndex << 1 | 1);
            }
            tree[treeIndex] = tree[treeIndex << 1] + tree[treeIndex << 1 | 1];
        }
    }
}
