package bytedance.位运算;

public class 颠倒二进制位 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            // 取n目前最低位，然后将其放到rev的最高位
            rev |= (n & 1) << (31 - i);
            // 无符号右移
            n >>>= 1;
        }
        return rev;
    }

    // a b c d e f g h
    // e f g h a b c d
    // g h e f c d a b
    // h g f e d c b a
    // 完成翻转
    public int optimize(int n) {
        // 翻转一个二进制串，可以进行分治，将字符串分为左右两份，然后分别进行翻转
        // 然后将左边翻转结果接到右边
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}
