package bytedance.设计;

import java.util.ArrayList;
import java.util.List;

public class 字符串编码与解码 {
    // 大小 + 字符串
    // 表示字符串长度的字节数组
    private static final int SIZE = 4;
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str: strs) {
            sb.append(mapStringLengthToBytes(str));
            sb.append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            int length = mapBytesToStringLength(s.substring(i, i + SIZE));
            res.add(s.substring(i + SIZE, i + SIZE + length));
            i += SIZE + length;
        }
        return res;
    }

    private String mapStringLengthToBytes(String str) {
        int length = str.length();
        char[] bytes = new char[SIZE];
        for (int i = SIZE - 1; i >= 0; i--) {
            bytes[SIZE - 1 - i] = (char)(length >>> (8 * i) & 0xff);
        }
        return new String(bytes);
    }

    private int mapBytesToStringLength(String bytes) {
        int length = 0;
        for (int i = 0; i < SIZE; i++) {
            length = (length << 8) + (bytes.charAt(i) & 0xff);
        }
        return length;
    }
}
