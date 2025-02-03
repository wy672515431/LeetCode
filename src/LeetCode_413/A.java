package LeetCode_413;

public class A {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int n1 = coordinate1.charAt(0) - 'a';
        int m1 = coordinate1.charAt(1) - '1';
        int n2 = coordinate2.charAt(0) - 'a';
        int m2 = coordinate2.charAt(1) - '1';
        //
        if (n1 % 2 == n2 % 2) {
            return m1 % 2 == m2 % 2;
        }
        return m1 % 2 != m2 % 2;
    }
}
