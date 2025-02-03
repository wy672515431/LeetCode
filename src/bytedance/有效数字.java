package bytedance;

public class 有效数字 {
    public boolean isNumber(String s) {
        // 可选符号-+后面跟数字
        // 数字后面跟一个小数点
        String validNumberPattern =
                "[-+]?(\\d+|\\d+\\.\\d*|\\.\\d+)([eE][-+]?\\d+)?";
        return s.matches(validNumberPattern);
    }
}
