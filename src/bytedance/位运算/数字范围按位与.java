package bytedance.位运算;

public class 数字范围按位与 {
    public int rangeBitwiseAnd(int left, int right) {
        // 找到二进制公共前缀
        int shift = 0;
        while (left < right) {
            left >>>= 1;
            right >>>= 1;
            shift++;
        }
        return left << shift;
    }

    public int rangeBitwiseAnd2(int left, int right) {
        // right &= right - 1 会将right的最右边的1变为0
        while (left < right) {
            right &= right - 1;
        }
        return right;
    }
}
