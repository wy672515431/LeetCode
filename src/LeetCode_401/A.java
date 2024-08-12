package LeetCode_401;

public class A {
    public int numberOfChild(int n, int k) {
        int flag = k / (n - 1);
        int pos = k % (n - 1);
        if ((flag & 1) == 0) {
            return pos;
        } else {
            return n - pos;
        }
    }
}
