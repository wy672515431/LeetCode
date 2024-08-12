package bytedance.线段树;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护两个线段树，对于每一次操作， 将区间[nums[i], nums[i]]的值置为1
 * 每次查询[nums[i] + 1, 1e9]区间和
 */
public class _3072 {
    private static final int N = (int) 1e9;

    public int[] resultArray(int[] nums) {
        SegmentTree sg1 = new SegmentTree(N);
        SegmentTree sg2 = new SegmentTree(N);
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        sg1.update(nums[0], 1);
        sg2.update(nums[1], 1);
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int sum1 = sg1.sum(nums[i] + 1, N);
            int sum2 = sg2.sum(nums[i] + 1, N);
            if (sum1 > sum2) {
                sg1.update(nums[i], 1);
                arr1.add(nums[i]);
            } else if (sum1 < sum2) {
                sg2.update(nums[i], 1);
                arr2.add(nums[i]);
            } else {
                if (arr1.size() <= arr2.size()) {
                    sg1.update(nums[i], 1);
                    arr1.add(nums[i]);
                } else {
                    sg2.update(nums[i], 1);
                    arr2.add(nums[i]);
                }
            }
        }
        int[] res = new int[arr1.size() + arr2.size()];
        arr1.addAll(arr2);
        for (int i = 0; i < res.length; i++) {
            res[i] = arr1.get(i);
        }
        return res;
    }

    /**
     * 线段树，动态开点
     */
    static class SegmentTree {
        private static class Node {
            int lazy, val;
            Node left, right;

            public Node() {
            }
        }

        int n;
        Node root;

        public SegmentTree(int n) {
            this.n = n;
            root = new Node();
        }

        public void update(int i, int x) {
            internalUpdate(i, x, 1, n, root);
        }

        private void internalUpdate(int i, int x, int start, int end, Node cur) {
            if (start == end) {
                cur.val += x;
                return;
            }
            addNode(cur);
            int mid = start + (end - start) / 2;
            if (cur.lazy != 0) {
                pushDown(start, mid, end, cur);
            }
            if (i <= mid) {
                internalUpdate(i, x, start, mid, cur.left);
            } else {
                internalUpdate(i, x, mid + 1, end, cur.right);
            }
            pushUp(cur);
        }

        public int sum(int l, int r) {
            return internalSum(l, r, 1, n, root);
        }

        private int internalSum(int l, int r, int start, int end, Node cur) {
            if (l <= start && end <= r) {
                return cur.val;
            }
            addNode(cur);
            int mid = start + (end - start) / 2;
            if (cur.lazy != 0) {
                pushDown(start, mid, end, cur);
            }
            int res = 0;
            if (l <= mid) {
                res += internalSum(l, r, start, mid, cur.left);
            }
            if (r > mid) {
                res += internalSum(l, r, mid + 1, end, cur.right);
            }
            return res;
        }

        private void pushDown(int start, int mid, int end, Node cur) {
            Node left = cur.left, right = cur.right;
            left.val = (mid - start + 1) * cur.lazy;
            left.lazy = cur.lazy;
            right.val = (end - mid) * cur.lazy;
            right.lazy = cur.lazy;
            cur.lazy = 0;
        }

        private void pushUp(Node cur) {
            cur.val = cur.left.val + cur.right.val;
        }

        private void addNode(Node cur) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
        }
    }
}
