package bytedance.字符串;

public class 字符串转整数 {
    public int myAtoi(String s) {
        // 预处理
        String preprocessedStr = s.stripLeading();
        boolean isPositive = true;
        int res = 0;
        for (int i = 0; i < preprocessedStr.length(); i++) {
            char ch = preprocessedStr.charAt(i);
            if (!Character.isDigit(ch)) {
                // 第一个字符可能是正负号，如果是其他非数字字符，直接返回0
                if (i == 0 && ch == '-') {
                    isPositive = false;
                } else if (i == 0 && ch == '+'){
                    // do nothing
                } else {
                    break;
                }
            } else {
                int num = ch - '0';
                // 溢出
                // Integer.MIN_VALUE % 10 == -8
                if (res < Integer.MIN_VALUE / 10
                        || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                    res = Integer.MIN_VALUE;
                    break;
                }
                // 我们计算时，是不带符号的，如果不转为负数计算，会导致Integer.MIN_VALUE溢出
                res = res * 10 - num;
            }
        }
        if (isPositive) {
            return res == Integer.MIN_VALUE ? Integer.MAX_VALUE : -res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(-2147483648 % 10);
    }
}
