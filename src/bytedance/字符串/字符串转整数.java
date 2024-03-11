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
