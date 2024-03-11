package LeetCode.BitOperation;

public class BitUtil {
    public static int zeroToOne(int origin, int bitIndex) {
        return origin |= (1 << bitIndex);
    }

    public static int oneToZero(int origin, int bitIndex) {
        return origin &= ~(1 << bitIndex);
    }

    public static int invert(int origin, int bitIndex) {
        return origin ^= (1 << bitIndex);
    }
}
