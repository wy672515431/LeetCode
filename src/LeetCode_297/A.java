package LeetCode_297;
public class A {
    public double calculateTax(int[][] brackets, int income) {
        double ans = 0.0;
        int preUpper = 0;
        for (int i = 0; i < brackets.length; i++) {
            int upper = brackets[i][0];
            int percent = brackets[i][1];
            if (income >= upper) {
                ans += (upper - preUpper) * percent * 1.0 / 100;
                preUpper = upper;
            } else {
                ans += (income - preUpper) * percent * 1.0 / 100;
                break;
            }
        }
        return ans;
    }
} 