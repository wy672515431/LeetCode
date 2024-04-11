package bytedance.递归;

public class 字符串解码 {
    int index = 0;
    public String decodeString(String s) {
        // 数字后面必须跟上[]
        return solve(s);
    }

    private String solve(String s) {
        StringBuilder res = new StringBuilder();
        int times = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (Character.isLetter(ch)) {
                res.append(ch);
                index++;
            } else if (Character.isDigit(ch)) {
                while (Character.isDigit(s.charAt(index))) {
                    times = times * 10 + (s.charAt(index) - '0');
                    index++;
                }
            } else if (ch == '[') {
                index++;
                res.repeat(solve(s), times);
                times = 0;
            } else if (ch == ']') {
                index++;
                return res.toString();
            }
        }
        return res.toString();
    }
}
