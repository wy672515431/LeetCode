package LeetCode.BitOperation;

public class 只出现一次的数字 {
    /**
     * 只有一个数字出现1次，其余数字出现3次
     * 思路：我们设计一个32位的数组，来统计各个位数上出现1的个数，因为如果所有数字全是3次，那么
     * bits[i]中的每一位对3进行取余都会等于0
     * bits[i] % 3组成的数字便是出现一次的个数
     *
     * 进阶：我们可以不利用数组，而是通过两个数字来表示
     * a的第i位为0，b的第i位为0，表示0
     * a = 0, b = 1 表示1
     * a = 1 b = 0 表示2
     *
     * ai bi xi ani bni
     * 0  0  0  0   0
     * 0  0  1  0   1
     * 0  1  0  0   1
     * 0  1  1  1   0
     * 1  0  0  1   0
     * 1  0  1  0   0
     *
     *
     * a = ai'bixi + aibi'xi'
     * b = bi'ai'xi + biai'xi' = ai'(bi ^ xi)
     * //最后结果一定和b有关，与a无关，00 01 不可能是10(因为只出现了一次)
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int a = 0;
        int b = 0;
        for (int num : nums) {
            int tem = a;
            a = (~a & b & num) | (a & ~b & ~num);
            b = ~tem & (b ^ num);
        }
        return b;
    }

    /**
     * 两个数字出现1次，其他的出现两次
     * 设两个数字为a,b，最后数组异或和sum = a ^ b;
     * 两个数字必然不同，需要找到他们的最低不同位，然后将数组分为两组
     * @param nums
     * @return
     */
    public int[] singleNumber1(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        int mask = result & (-result);
        int[] ans = new int[2];
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        ans[0] = a;
        ans[1] = b;
        return ans;
    }
}
