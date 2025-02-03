package LCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class D {
    private static final int MAX = (int) 1e9;
    private static final int MOD = (int)1e9 + 7;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wf = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] a = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        SegmentTree sg = new SegmentTree(MAX + 1);
        long ans = 0L;
        for (int i = 0; i < n; i++) {
            sg.update(a[i], 1);
            ans += 1;
            if (a[i] + 1 <= MAX) {
                long res = sg.query(a[i] + 1, MAX);
                ans = (ans + res) % MOD;
                sg.update(a[i], res);
            }
        }
        wf.write(String.valueOf(ans));
        wf.flush();
        bf.close();
        wf.close();
    }

    static class SegmentTree {
        static class Node {
            Node left;
            Node right;
            long val;
        }

        Node root;
        int n;
        SegmentTree(int n) {
            this.n = n;
            root = new Node();
        }

        public void update(int index, long val) {
            update(index, val, 1, MAX, root);
        }

        private void update(int index, long val, int s, int e, Node cur) {
            if (s == e) {
                cur.val = (cur.val + val) % MOD;
                return;
            }
            addNode(cur);
            int mid = (e - s) / 2 + s;
            if (index <= mid) {
                update(index, val, s, mid, cur.left);
            } else {
                update(index, val, mid + 1, e, cur.right);
            }
            pushUp(cur);
        }

        public long query(int l, int r) {
            return query(l, r, 1, MAX, root);
        }

        private long query(int l, int r, int s, int e, Node cur) {
            if (l <= s && r >= e) {
                return cur.val;
            }
            addNode(cur);
            int mid = (e - s) / 2 + s;
            long ans = 0L;
            if (l <= mid) {
                ans = (ans + query(l, r, s, mid, cur.left)) % MOD;
            }
            if (r > mid) {
                ans = (ans + query(l, r, mid + 1, e, cur.right)) % MOD;
            }
            return ans;
        }

        private void addNode(Node cur) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
        }

        private void pushUp(Node cur) {
            cur.val = (cur.left.val + cur.right.val) % MOD;
        }
    }
}
