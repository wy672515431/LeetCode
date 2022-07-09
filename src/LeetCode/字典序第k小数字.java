package LeetCode;

public class 字典序第k小数字 {
    //想象成一颗字典树,其按字典序排序。
    //假设一个节点大小为n,则其子节点的大小为 10 * n ~ 10 * n + 9
    //如果按照前序遍历第k个，则为所需。
    //我们不妨这样想，对于第i小的数字，我们还需要找到k - i个数字
    //对于第i小的数字,其下面的子节点数量如果大于等于k - i，则我们需要的数字在其子节点下面。
    //如果小于k -i ，则可能在其兄弟节点上。

    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(cur, n);
            if (k <= steps) {
                cur = cur * 10;
                k--;
            } else {
                k -= steps;
                cur++;
            }
        }
        return cur;
    }

    private int getSteps(int cur, int n) {
        int steps = 0;
        int first = cur;
        int last = cur;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }
}
