package LeetCode;

public class 所有奇数长度子数组的和 {
    /**
     * 利用前缀和可以实现O(N^2)的复杂度
     * 本方法介绍复杂度为O(N)的算法
     * 计算每个元素在多少个长度为奇数的子数组中出现
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            //左边元素个数
            int leftCount = i;
            //右边元素个数
            int rightCount = arr.length - i - 1;
            int leftOdd = (leftCount + 1) / 2;
            int rightOdd = (rightCount + 1) / 2;
            //0包括
            int leftEven = (leftCount / 2) + 1;
            int rightEven = (rightCount / 2) + 1;

            ans += (leftOdd * rightOdd + leftEven * rightEven) * arr[i];
        }
        return  ans;
    }
}
