package bytedance.字符串;

public class 字符串相乘 {
    public String multiply(String num1, String num2) {
        StringBuilder left = new StringBuilder("0");
        StringBuilder right = new StringBuilder();
        int len1 = num1.length(), len2 = num2.length();
        for (int i = len1 - 1; i >= 0; i--) {
            right.setLength(0);
            char ch1 = num1.charAt(i);
            int carry = 0;
            int j = len2 - 1;
            while (j >= 0 || carry != 0) {
                int num = j < 0 ? 0 : num2.charAt(j) - '0';
                int mult = (ch1 - '0') * num + carry;
                right.append(mult % 10);
                carry = mult / 10;
                j--;
            }
            right.reverse();
            // 在后面添加后缀0
            right.repeat("0", len1 - i - 1);
            left = add(left, right);
        }
        if (left.charAt(0) == '0') {
            left.setLength(1);
        }
        return left.toString();
    }

    private StringBuilder add(StringBuilder left, StringBuilder right) {
        int i = left.length() - 1, j = right.length() - 1;
        StringBuilder res = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int num1 = i < 0 ? 0 : left.charAt(i) - '0';
            int num2 = j < 0 ? 0 : right.charAt(j) - '0';
            int sum = num1 + num2 + carry;
            res.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        return res.reverse();
    }
}
