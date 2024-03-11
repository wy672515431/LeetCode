package LeetCode_321;

public class A {
    public int pivotInteger(int n) {
        for (int i = 1; i <= n; i++) {
            if (getSum(1, i) == getSum(i, n)) {
                return i;
            }
        }
        return -1;
    }

    public int getSum(int start, int end) {
        return (start + end) * (end - start + 1) / 2;
    }
}
