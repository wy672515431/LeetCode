package bytedance.线段树;

import java.util.Arrays;

public class 最长递增子序列II {
        /**
         * 子序列中相邻元素的差值不超过k
         * dp[i]表示以nums[i]结尾的最长递增子序列的长度, dp[i] = max(dp[j] + 1) (0 <= j < i && nums[i] - nums[j] <= k)
         * @param nums
         * @param k
         * @return
         */
        public int lengthOfLIS(int[] nums, int k) {
            int maxVal = Arrays.stream(nums).max().orElse(1);
            SegmentTree segmentTree = new SegmentTree(maxVal + 1);
            for (int i = 0; i < nums.length; i++) {
                int curLen = 1 + segmentTree.query(Math.max(0, nums[i] - k), nums[i] - 1);
                segmentTree.update(nums[i], curLen);
            }
            return segmentTree.query(0, maxVal);
        }

        static class SegmentTree {
            int n;
            int[] tree;

            public SegmentTree(int n) {
                this.n = n;
                tree = new int[4 * n];
            }

            public int query(int l, int r) {
                return query(l, r, 0, n - 1, 1);
            }

            private int query(int l, int r, int start, int end, int treeIndex) {
                if (l <= start && r >= end) {
                    return tree[treeIndex];
                }
                int mid = (end - start) / 2 + start;
                int res = Integer.MIN_VALUE;
                if (l <= mid) {
                    res = Math.max(res, query(l, r, start, mid, treeIndex * 2));
                }
                if (r > mid) {
                    res = Math.max(res, query(l, r, mid + 1, end, treeIndex * 2 + 1));
                }
                return res;
            }

            // 将点index更新为value
            public void update(int index, int value) {
                update(index, 0, n - 1, 1, value);
            }

            private void update(int index, int start, int end, int treeIndex, int val) {
                if (start == end) {
                    tree[treeIndex] = val;
                    return;
                }
                int mid = (end - start) / 2 + start;
                if (index <= mid) {
                    update(index, start, mid, treeIndex * 2, val);
                } else {
                    update(index, mid + 1, end, treeIndex * 2 + 1, val);
                }
                pushUp(treeIndex);
            }

            private void pushUp(int index) {
                tree[index] = Math.max(tree[index * 2], tree[index * 2 + 1]);
            }
        }
}
