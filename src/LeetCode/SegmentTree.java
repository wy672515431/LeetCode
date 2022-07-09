package LeetCode;

import java.util.Arrays;

/**
 * 线段树中的节点代表线段。子节点就是将父节点给对半分开。
 * 最后一层的每个节点均代表长度为1的线段。
 * task one : sum of the [L, R]
 * 每个节点记录节点对应区间数字的和
 * task two: change the val of x[L]
 * 找到区间[L,L]的节点，更新从根到该节点路径上所有节点的值
 * 可以在4n的存储空间存储线段树
 */
public class SegmentTree {
    private int[] nums;
    private int[] segmentTree;

    /**
     * 
     * @param array array的数组从0 ~ array.length - 1;
     */
    public SegmentTree(int[] array) {
        nums = new int[array.length + 1];
        segmentTree = new int[4 * array.length + 1];
        System.arraycopy(array, 0, nums, 1, array.length);
        buildTree(1, 1, array.length);
    }

    /**
     * 
     * @param index 标号为index的点
     * @param left  标号为index点对应的区间的左边界
     * @param right 标号为index点对应区间的右边界
     */
    public void buildTree(int index, int left, int right) {
        if (left == right) {
            segmentTree[index] = nums[left];
            return;
        }
        int mid = (left + right) >> 1;
        //左子树
        buildTree(2 * index, left, mid);
        buildTree(2 * index + 1, mid + 1, right);
        segmentTree[index] = segmentTree[2 * index] + segmentTree[2 * index + 1];
    }

    /**
     * 
     * @param index 标号为index的点
     * @param left 标号为index点对应的区间的左边界
     * @param right 标号为index点对应区间的右边界
     * @param changeIndex 需要修改的点,包含在[left, right]
     * @param val  加上的值
     */
    public void update(int index, int left, int right, int changeIndex, int val) {
        segmentTree[index] += val;
        if (left == right)
            return;
        int mid = (left + right) >> 1;
        if (changeIndex <= mid) {
            update(2 * index, left, mid, changeIndex, val);
        } else {
            update(2 * index + 1, mid + 1, right, changeIndex, val);
        }
    }

    /**
     * 
     * @param index 标号为index的点
     * @param left 标号为index点对应的区间的左边界
     * @param right 标号为index点对应区间的右边界
     * @param start 查询区间的左边界
     * @param end 查询区间的右边界
     * @return
     */
    public int query(int index, int left, int right, int start, int end) {
        if (left == start && right == end) {
            return segmentTree[index];
        }
        int mid = (left + right) >> 1;
        if (end <= mid) {
            return query(2 * index, left, mid, start, end);
        } else if (start > mid) {
            return query(2 * index + 1, mid + 1, right, start, end);
        } else {
            return query(2 * index, left, mid, start, mid) + query(2 * index + 1, mid + 1, right, mid + 1, end);
        }
    }
}