package LeetCode;

public class 负进制 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        //保证余数为非负数
        // 2  2 / -2 = -1 .... 0
        // -1 / -2 = 1 .... 1
        // 1 / -2 = 0 .... 1
        while (n != 0) {
            int div = n / (-2);
            int mod = n % (-2);
            if (mod < 0) {
                div += 1;
                mod += 2;
            }
            sb.append(mod);
            n = div;
        }
        return sb.reverse().toString();
    }
}
