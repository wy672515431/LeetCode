package bytedance.线段树;

public class 由单个字符重复的最长子字符串 {
    public static void main(String[] args) {
        由单个字符重复的最长子字符串 s = new 由单个字符重复的最长子字符串();
        String str = "abyzz";
        String queryCharacters = "aa";
        int[] queryIndices = {2, 1};
        int[] res = s.longestRepeating(str, queryCharacters, queryIndices);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        SegmentTree sg = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            sg.update(i, s.charAt(i));
        }
        int k = queryIndices.length;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            sg.update(queryIndices[i], queryCharacters.charAt(i));
            res[i] = sg.query(0, n - 1);
        }
        return res;
    }


    static class Node {
        // 代表该区间单字符重复的最长子字符串
        // 两个区间合并的时候，不是简单的取大
        // 我们要考虑左区间的后缀和右区间的前缀
        char ch;
        int len;
        int prefix;
        int suffix;
        char pch;
        char sch;

        Node(char ch, int len, int prefix, int suffix, char pch, char sch) {
            this.ch = ch;
            this.len = len;
            this.prefix = prefix;
            this.suffix = suffix;
            this.pch = pch;
            this.sch = sch;
        }

        Node() {

        }
    }

    static class SegmentTree {
        int n;
        Node[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new Node[n << 2];
            for (int i = 0; i < n << 2; i++) {
                tree[i] = new Node();
            }
        }

        public int query(int l, int r) {
            return query(l, r, 0, n - 1, 1);
        }

        private int query(int l, int r, int s, int e, int treeIndex) {
            if (l <= s && r >= e) {
                return tree[treeIndex].len;
            }
            int mid = (e - s) / 2 + s;
            int res = Integer.MIN_VALUE;
            if (l <= mid) {
                res = Math.max(res, query(l, r, s, mid, treeIndex << 1));
            }
            if (r > mid) {
                res = Math.max(res, query(l, r, mid + 1, e, treeIndex << 1 | 1));
            }
            return res;
        }

        public void update(int i, char ch) {
            update(i, ch, 0, n - 1, 1);
        }

        private void update(int i, char ch, int s, int e, int treeIndex) {
            if (s == e) {
                tree[treeIndex] = new Node(ch, 1, 1, 1, ch, ch);
                return;
            }
            int mid = (e - s) / 2 + s;
            if (i <= mid) {
                update(i, ch, s, mid, treeIndex << 1);
            } else {
                update(i, ch, mid + 1, e, treeIndex << 1 | 1);
            }
            pushUp(treeIndex, e - s + 1);
        }

        private void pushUp(int treeIndex, int len) {
            Node left = tree[treeIndex << 1];
            Node right = tree[treeIndex << 1 | 1];
            int maxLen;
            char ch;
            if (left.len > right.len) {
                maxLen = left.len;
                ch = left.ch;
            } else {
                maxLen = right.len;
                ch = right.ch;
            }
            int prefix = left.prefix;
            int suffix = right.suffix;
            if (left.sch == right.pch) {
                if (left.suffix + right.prefix > maxLen) {
                    maxLen = left.suffix + right.prefix;
                    ch = left.sch;
                }
                if (prefix == (len + 1) / 2) {
                    prefix += right.prefix;
                }
                if (suffix == len / 2){
                    suffix += left.suffix;
                }
            }
            System.out.println("treeIndex = " + treeIndex + " ch = " + ch + " maxLen = " + maxLen + " prefix = " + prefix + " suffix = " + suffix);
            // 需要更新left.prefix 和 right.suffix
            tree[treeIndex] = new Node(ch, maxLen, prefix, suffix, left.pch, right.sch);
        }
    }
}
