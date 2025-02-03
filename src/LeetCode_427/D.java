package LeetCode_427;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class D {
    private static final int N = (int)8e7;
    public int maxRectangleArea(int[] xCoord, int[] yCoord) {
        int ans = -1;
        int[][] points = new int[xCoord.length][2];
        for (int i = 0; i < xCoord.length; i++) {
            points[i] = new int[]{xCoord[i], yCoord[i]};
        }
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        // key 是 x 坐标，value 是 y 坐标的集合
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        // key 是 y 坐标，value 是 x 坐标的集合
        Map<Integer, TreeSet<Integer>> map1 = new HashMap<>();
        SegmentTree sg = new SegmentTree(N);
        for (int[] point : points) {
            int x = point[0], y = point[1];
            TreeSet<Integer> set = map.getOrDefault(x, new TreeSet<>());
            TreeSet<Integer> set1 = map1.getOrDefault(y, new TreeSet<>());
            set.add(y);
            set1.add(x);
            map.put(x, set);
            map1.put(y, set1);
        }
        map.forEach(sg::update);
        for (int[] point : points) {
            // 左下脚顶点
            int x = point[0], y = point[1];
            // 找到右下角顶点
            TreeSet<Integer> set = map1.get(y);
            Integer right = set.higher(x);
            if (right == null) {
                continue;
            }
            // 找到左上角顶点
            TreeSet<Integer> set1 = map.get(x);
            Integer up = set1.higher(y);
            if (up == null) {
                continue;
            }
            // 确定右上角顶点
            TreeSet<Integer> set2 = map1.get(up);
            TreeSet<Integer> set3 = map.get(right);
            if (!set2.contains(right) || !up.equals(set3.higher(y))) {
                continue;
            }
            // 确定矩形内部是否存在点
            TreeSet<Integer> set4 = sg.query(x + 1, right - 1);
            if (set4.higher(y) == null || set4.higher(y) > up) {
                ans = Math.max(ans, (right - x) * (up - y));
            }
        }
        return ans;
    }


    /**
     *
     */
    static class SegmentTree {
        static class Node {
            TreeSet<Integer> val;
            SegmentTree.Node left, right;

            Node() {
                val = new TreeSet<>();
            }
        }

        SegmentTree.Node root;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            root = new SegmentTree.Node();
        }

        public TreeSet<Integer> query(int l, int r) {
            return query(l, r, 0, n, root);
        }

        private TreeSet<Integer> query(int l, int r, int start, int end, SegmentTree.Node cur) {
            if (l > r) {
                return new TreeSet<>();
            }
            if (l <= start && r >= end) {
                return cur.val;
            }
            addNode(cur);
            int mid = start + (end - start) / 2;
            TreeSet<Integer> res = new TreeSet<>();
            if (l <= mid) {
                res.addAll(query(l, r, start, mid, cur.left));
            }
            if (r > mid) {
                res.addAll(query(l, r, mid + 1, end, cur.right));
            }
            return res;
        }

        public void update(int idx, TreeSet<Integer> val) {
            update(idx, 0, n, val, root);
        }

        private void update(int idx, int start, int end, TreeSet<Integer> val, SegmentTree.Node cur) {
            if (start == end) {
                cur.val = val;
                return;
            }
            addNode(cur);
            int mid = start + (end - start) / 2;
            if (idx <= mid) {
                update(idx, start, mid, val, cur.left);
            } else {
                update(idx, mid + 1, end, val, cur.right);
            }
            pushUp(cur);
        }

        private void addNode(SegmentTree.Node cur) {
            if (cur.left == null) {
                cur.left = new SegmentTree.Node();
            }
            if (cur.right == null) {
                cur.right = new SegmentTree.Node();
            }
        }

        private void pushUp(SegmentTree.Node cur) {
            if (cur.left != null) {
                cur.val.addAll(cur.left.val);
            }
            if (cur.right != null) {
                cur.val.addAll(cur.right.val);
            }
        }
    }
}
