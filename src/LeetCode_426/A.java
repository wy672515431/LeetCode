package LeetCode_426;

public class A {
    public int smallestNumber(int n) {
        int len = Integer.toBinaryString(n).length();
        return (1 << len) - 1;
    }
}
