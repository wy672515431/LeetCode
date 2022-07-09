package LeetCode;

public class 统计二进制中一的个数 {
    /**
     * 如果一个正数不为0,那么整数至少有一位1.如果我们这个整数减1
     * 那么原来在整数最右边的1就会变为0，它后面的0就会变为1.而前面的1保持不变。
     * 二者相与
     * @param n
     * @return
     */
    public int numberOf1 (int n) {
//        Integer.bitCount(n);
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
