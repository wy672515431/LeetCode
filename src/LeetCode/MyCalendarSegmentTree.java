package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class MyCalendarSegmentTree {
    /*
     * 利用线段树,开辟数组[0, 1e9],初始每个元素的值为0，对于每次行程预定的区间[start, end),我们将数组中对于区间的元素置为1。
     * 每次调用book时，我们检测数组区间中是否有元素为1，采用动态线段树
     * 懒标记标记区间[l,r]已被预订，tree记录区间[l,r]是否存在标记为1的元素
     */
    Set<Integer> tree;
    Set<Integer> lazy;

    public MyCalendarSegmentTree() {
        tree = new HashSet<>();
        lazy = new HashSet<>();
    }

    public boolean book(int start, int end) {
        if (query(start, end - 1, 0, 1000000000, 1)) {
            return false;
        }
        update(start, end - 1, 0, 1000000000, 1);
        return true;
    }

    /**
     * 
     * @param start
     * @param end
     * @param l 标号为idx的点代表线段的左边界
     * @param r 标号为idx的点代表线段的右边界
     * @param idx 标号为idx的点
     * @return
     */
    public boolean query(int start, int end, int l, int r, int idx) {
        if (start > r || end < l) {
            return false;
        }
        //如果区间已经被预定，直接返回 
        if (lazy.contains(idx)) {
            return true;
        }
        if (start <= l && end >= r) {
            return tree.contains(idx);
        } else {
            int mid = (l + r) >> 1;
            if (end <= mid) {
                return query(start, end, l, mid, 2 * idx);
            } else if (start > mid) {
                return query(start, end, mid + 1, r,  2 *idx + 1);
            } else {
                return query(start, end, l, mid, 2 * idx) || query(start, end, mid + 1, r, 2 * idx + 1);
            }
        }
    }

    public void update(int start, int end, int l, int r, int idx) {
        if (start > r|| end < l) {
            return;
        }
        if (start <= l && end >= r) {
            tree.add(idx);
            lazy.add(idx);
        } else {
            int mid = (l + r) >> 1;
            update(start, end, l, mid, 2 * idx);
            update(start, end, mid + 1, r, 2 * idx + 1);
            tree.add(idx);
        }
    }
}
