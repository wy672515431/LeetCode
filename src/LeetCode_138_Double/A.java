package LeetCode_138_Double;

public class A {
    public int generateKey(int num1, int num2, int num3) {
        StringBuilder sb = new StringBuilder();
        while (num1 > 0 || num2 > 0 || num3 > 0) {
            int n1 = num1 > 0 ? num1 % 10 : 0;
            int n2 = num2 > 0 ? num2 % 10 : 0;
            int n3 = num3 > 0 ? num3 % 10 : 0;
            sb.append(Math.min(n1, Math.min(n2, n3)));
            num1 /= 10;
            num2 /= 10;
            num3 /= 10;
        }
        return Integer.parseInt(sb.reverse().toString());
    }
}
