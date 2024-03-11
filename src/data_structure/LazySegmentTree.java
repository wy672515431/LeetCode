package data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LazySegmentTree {
    private SegNode[] arr;

    public LazySegmentTree(int[] nums) {
        int len = nums.length;
        arr = new SegNode[4 * len + 1];
        buildTree(1, 0, len - 1, nums);
    }

    public int sumRange(int left, int right) {
        return query(1, left, right);
    }

    public void reverseRange(int left, int right) {
        modify(1, left, right);
    }

    /**
     * 构造线段树，该线段树的区间操作是将0变成1，1变成0
     * @param index 线段树节点标号, arr[1]代表区间[0, len - 1]
     * @param left 区间左端点
     * @param right 区间右端点
     * @param nums 原数组
     */
    public void buildTree(int index, int left, int right, int[] nums) {
        if (left == right) {
            arr[index] = new SegNode(left, right, nums[left], false);
            return;
        }
        int mid = (left + right) >> 1;
        buildTree(2 * index, left, mid, nums);
        buildTree(2 * index + 1, mid + 1, right, nums);
        arr[index] = new SegNode(left, right, arr[2 * index].val + arr[2 * index + 1].val, false);
    }

    /**
     * 下传懒标记,即将当前区间的修改情况下传到其左右孩子结点
     * @param index 线段树节点标号
     */
    public void pushdown(int index) {
        if (arr[index].lazy) {
            arr[2 * index].lazy = !arr[2 * index].lazy;
            arr[2 * index + 1].lazy = !arr[2 * index + 1].lazy;
            //区间反转
            arr[2 * index].val = (arr[2 * index].right - arr[2 * index].left + 1) - arr[2 * index].val;
            arr[2 * index + 1].val = (arr[2 * index + 1].right - arr[2 * index + 1].left + 1) - arr[2 * index + 1].val;
            arr[index].lazy = false;
        }
    }

    /**
     * 进行区间反转
     * @param index 线段树节点标号
     * @param left 修改区间的左端点
     * @param right 修改区间的右端点
     */
    public void modify(int index, int left, int right) {
        // 该节点表示的区间在[left, right]之间，直接进行修改
        if (arr[index].left >= left && arr[index].right <= right) {
            arr[index].lazy = !arr[index].lazy;
            arr[index].val = (arr[index].right - arr[index].left + 1) - arr[index].val;
            return;
        }
        // 进行更新
        pushdown(index);
        if (arr[2 * index].right >= left) {
            modify(2 * index, left, right);
        }
        if (arr[2 * index + 1].left <= right) {
            modify(2 * index + 1, left, right);
        }
        arr[index].val = arr[2 * index].val + arr[2 * index + 1].val;
    }

    /**
     * 查询区间[left, right]的和
     * @param index 线段树节点标号
     * @param left  查询区间的左端点
     * @param right 查询区间的右端点
     * @return 查询区间的和
     */
    public int query(int index, int left, int right) {
        if (arr[index].left >= left && arr[index].right <= right) {
            return arr[index].val;
        }
        if (arr[index].left > right || arr[index].right < left) {
            return 0;
        }
        pushdown(index);
        int sum = 0;
        if (arr[2 * index].right >= left) {
            sum += query(2 * index, left, right);
        }
        if (arr[2 * index + 1].left <= right) {
            sum += query(2 * index + 1, left, right);
        }
        return sum;
    }
}

class SegNode {
    public int left;
    public int right;
    public int val;
    public boolean lazy;

    public SegNode(int left, int right, int val, boolean lazy) {
        this.left = left;
        this.right = right;
        this.val = val;
        this.lazy = lazy;
    }
}
