package LCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wf = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = bf.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);
        int[] a = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long[] max = new long[n + 1];
        Arrays.fill(max, Long.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            long sum = 0L;
            for (int j = i; j < n; j++) {
                sum += a[j];
                max[j - i + 1] = Math.max(max[j - i + 1], sum);
            }
        }
        HashMap<Interval, Long> ansMap = new HashMap<>();
//        SegmentTree sg = new SegmentTree(n + 1);
//        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
//            int key = entry.getKey();
//            long val = entry.getValue();
//            sg.update(key, val);
//        }
        for (int i = 0; i < n; i++) {
            long ans = Long.MIN_VALUE;
            for (int j = i; j < n; j++) {
                ans = Math.max(ans, max[j + 1]);
                Interval interval = new Interval(i + 1, j + 1);
//                long ans = sg.query(i + 1, j + 1);
                ansMap.put(interval, ans);
            }
        }
        while (q-- > 0) {
            input = bf.readLine().split(" ");
            int l = Integer.parseInt(input[0]);
            int r = Integer.parseInt(input[1]);
            long ans = ansMap.get(new Interval(l, r));
            wf.write(String.valueOf(ans));
            wf.newLine();
        }
        bf.close();
        wf.close();
    }

    static class Interval {
        int l;
        int r;
        Interval(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return l == interval.l && r == interval.r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l, r);
        }
    }

    static class SegmentTree {
        int n;
        long[] tree;
        SegmentTree(int n) {
            this.n = n;
            this.tree = new long[4 * n];
        }

        public void update(int index, long val) {
            update(index, val, 1, n, 1);
        }

        private void update(int index, long val, int l, int r, int treeIndex) {
            if (l == r) {
                tree[treeIndex] = val;
                return;
            }
            int mid = (r - l) / 2 + l;
            if (index <= mid) {
                update(index, val, l, mid, treeIndex * 2);
            } else {
                update(index, val, mid + 1, r, treeIndex * 2 + 1);
            }
            pushUp(treeIndex);
        }

        private void pushUp(int treeIndex) {
            tree[treeIndex] = Math.max(tree[treeIndex * 2], tree[treeIndex * 2 + 1]);
        }

        public long query(int l, int r) {
            return query(l, r, 1, n, 1);
        }

        private long query(int l, int r, int s, int e, int treeIndex) {
            if (l <= s && r >= e) {
                return tree[treeIndex];
            }
            int mid = (e - s) / 2 + s;
            long ans = Long.MIN_VALUE;
            if (l <= mid) {
                ans = Math.max(ans, query(l, r, s, mid, treeIndex * 2));
            }
            if (r > mid) {
                ans = Math.max(ans, query(l, r, mid + 1, e, treeIndex * 2 + 1));
            }
            return ans;
        }
    }
}
