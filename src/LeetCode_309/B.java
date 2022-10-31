package LeetCode_309;

public class B {
    static final int mod = 1000000007;
    //假设endPos > startPos
    //如果endPos - startPos > k 说明不存在相关方法
    //如果endPos - startPos = k 存在一种
    //如果endPos - startPos < k 应为肯定存在向右走 endPos - startPos步
    //剩下的k - (endPos - startPos)步为偶数可以随机走掉，如果不是偶数步，则完成不了
    //组合数学
    public int numberOfWays(int startPos, int endPos, int k) {
        if (Math.abs(endPos -  startPos) > k) {
            return 0;
        } else if (Math.abs(endPos - startPos) == k) {
            return 1;
        } else {
            int actualStep = Math.abs(endPos - startPos);
            int leftStep = k - actualStep;
            if (leftStep % 2 != 0) {
                return 0;
            } else {
                int m = actualStep + 1;
                int n = leftStep / 2;
                int[][] combine = new int[n + m + 1][m + 1];
                int len = combine.length;
                //C(n + m - 1, m - 1)
                combine[0][0] = 1;
                for (int i = 1; i < len; i++) {
                    combine[i][0] = 1;
                    for (int j = 1; j <= Math.min(i, m); j++) {
                        combine[i][j] = (combine[i - 1][j - 1] + combine[i - 1][j]) % mod;
                    }
                }
                return combine[n + m - 1][m - 1];
            }
        }
    }

    public static void main(String[] args) {
        new B().numberOfWays(1, 2, 3);
    }

}
