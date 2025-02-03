package bytedance.位运算;

public class 二进制求和 {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int n1 = i >= 0 ? a.charAt(i--) - '0' : 0;
            int n2 = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = n1 + n2 + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}
