package bytedance.线段树;

import java.util.ArrayDeque;
import java.util.Deque;

public class 最多K次交换相邻数位后得到的最小整数 {
    public static void main(String[] args) {
        最多K次交换相邻数位后得到的最小整数 s = new 最多K次交换相邻数位后得到的最小整数();
        System.out.println(s.minInteger("4321", 4));
    }

    public String minInteger(String num, int k) {
        int n = num.length();
        StringBuilder res = new StringBuilder();
        Deque<Integer>[] deques = new Deque[10];
        for (int i = 0; i < 10; i++) {
            deques[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            deques[digit].addLast(i);
        }
        SegmentTree sg = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            // 从小到大
            for (int j = 0; j < 10; j++) {
                if (!deques[j].isEmpty()) {
                    int pos = deques[j].peekFirst();
                    int dist = pos - i + sg.query(pos, n - 1);
                    if (dist <= k) {
                        // 交换
                        res.append(j);
                        sg.update(pos, 1);
                        deques[j].pollFirst();
                        k -= dist;
                        break;
                    }
                }
            }
        }
        return res.toString();
    }

    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[n << 2];
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
                update(i, val, s, mid, treeIndex << 1);
            } else {
                update(i, val, mid + 1, e, treeIndex << 1 | 1);
            }
            tree[treeIndex] = tree[treeIndex << 1] + tree[treeIndex << 1 | 1];
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
    }
}
