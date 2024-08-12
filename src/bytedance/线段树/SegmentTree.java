package bytedance.线段树;

public class SegmentTree {
    int[] nums;
    int[] tree;
    int[] lazy;
    int n;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[4 * n];
        this.lazy = new int[4 * n];
        build(0, n - 1, 1);
    }

    /**
     * 构建线段树
     *
     * @param start nums数组的起始位置
     * @param end  nums数组的结束位置
     * @param index 线段树tree数组的下标, tree[1]表示整个区间的和, 从1开始
     */
    private void build(int start, int end, int index) {
        // 叶子节点
        if (start == end) {
            tree[index] = nums[start];
            return;
        }
        int mid = start + (end - start) / 2;
        build(start, mid, index * 2);
        build(mid + 1, end, index * 2 + 1);
        pushUp(index);
    }

    /**
     * 更新tree[index]
     * @param index tree数组的下标
     */
    private void pushUp(int index) {
        tree[index] = tree[index * 2] + tree[index * 2 + 1];
    }

    /**
     * num[numIndex]更新为x
     * @param numIndex nums数组的下标
     * @param x 更新的值
     */
    public void update(int numIndex, int x) {
        internalUpdate(numIndex, x, 0, n - 1, 1);
    }

    /**
     * 更新线段树
     * @param numIndex nums数组的下标
     * @param x nums[numIndex]更新为x
     * @param start nums数组的起始位置
     * @param end   nums数组的结束位置
     * @param treeIndex 线段树tree数组的下标
     */
    private void internalUpdate(int numIndex, int x, int start, int end, int treeIndex) {
        if (start == end) {
            tree[treeIndex] = x;
            return;
        }
        int mid = start + (end - start) / 2;
        if (lazy[treeIndex] != 0) {
            pushDown(start, mid, end, treeIndex);
        }
        if (numIndex <= mid) {
            internalUpdate(numIndex, x, start, mid, treeIndex * 2);
        } else {
            internalUpdate(numIndex, x, mid + 1, end, treeIndex * 2 + 1);
        }
        // 更新父节点
        pushUp(treeIndex);
    }

    /**
     * 查询区间和
     * @param numLeft 区间左端点
     * @param numRight 区间右端点
     * @return 区间和
     */
    public int query(int numLeft, int numRight) {
        return internalQuery(numLeft, numRight, 0, n - 1, 1);
    }

    private int internalQuery(int numLeft, int numRight, int start, int end, int treeIndex) {
        // numLeft == start && numRight == end
        if (numLeft <= start && numRight >= end) {
            return tree[treeIndex];
        }
        int mid = start + (end - start) / 2;
        int sum = 0;
        if (lazy[treeIndex] != 0) {
            pushDown(start, mid, end, treeIndex);
        }
        if (numLeft <= mid) {
            sum += internalQuery(numLeft, numRight, start, mid, treeIndex * 2);
        }
        if (numRight > mid) {
            sum += internalQuery(numLeft, numRight, mid + 1, end, treeIndex * 2 + 1);
        }
        return sum;
    }

    /**
     * 区间更新
     * @param numLeft 区间左端点
     * @param numRight 区间右端点
     * @param x 更新的值
     */
    public void add(int numLeft, int numRight, int x) {
        internalAdd(numLeft, numRight, x, 0, n - 1, 1);
    }

    private void internalAdd(int numLeft, int numRight, int x, int start, int end, int treeIndex) {
        if (numLeft <= start && numRight >= end) {
            tree[treeIndex] += x * (end - start + 1);
            // 叶子节点就没必要再往下更新了
            if (start != end) {
                lazy[treeIndex] += x;
            }
            return;
        }
        int mid = start + (end - start) / 2;
        if (lazy[treeIndex] != 0) {
            pushDown(start, mid, end, treeIndex);
        }
        if (numLeft <= mid) {
            internalAdd(numLeft, numRight, x, start, mid, treeIndex * 2);
        }
        if (numRight > mid) {
            internalAdd(numLeft, numRight, x, mid + 1, end, treeIndex * 2 + 1);
        }
        pushUp(treeIndex);
    }

    private void pushDown(int start, int mid, int end, int treeIndex) {
        // 更新左右子节点
        tree[treeIndex * 2] += lazy[treeIndex] * (mid - start + 1);
        // 传递懒标记
        lazy[treeIndex * 2] += lazy[treeIndex];
        tree[treeIndex * 2 + 1] += lazy[treeIndex] * (end - mid);
        lazy[treeIndex * 2 + 1] += lazy[treeIndex];
        lazy[treeIndex] = 0;
    }


}
