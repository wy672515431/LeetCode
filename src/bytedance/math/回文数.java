package bytedance.math;

public class 回文数 {
    /**
     * 判断x为一个回文数，不能将x转变为字符串
     * 13231 -> 我们将x的一半进行反转，比如将31 -> 13，判断是否和前面一样
     * 如何判断已经过了一半？反转的部分大于等于前面
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 负数，最后一位为0但不是0的数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedNum = 0;
        while (x > reversedNum) {
            reversedNum = reversedNum * 10 + x % 10;
            x /= 10;
        }
        // 前者是偶数，后者是奇数
        return x == reversedNum || x == reversedNum / 10;
    }
}
