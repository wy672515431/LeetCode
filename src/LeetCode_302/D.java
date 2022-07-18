package LeetCode_302;

import java.util.*;

public class D {
    /**
     * 元素 x若能整除numsDivide的所有元素，等价于x是所有numsDivide[i]的因子，
     * 这也等价于x是numsDivide所有元素的最大公因数g的因子。
     * @param nums
     * @param numsDivide
     * @return
     */
    public int minOperations(int[] nums, int[] numsDivide) {
        int g = 0;
        for (int numDivide : numsDivide) {
            g = gcd(g, numDivide);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (g % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

}
