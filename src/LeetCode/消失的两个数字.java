package LeetCode;

public class 消失的两个数字 {
    /**
     * 我们先考虑缺失一个数字
     * 数字范围在1 ~ nums.length + 2
     * 假设我们的数组为[1,3]，则缺失的数字为2
     * sum = (1 + nums.length + 1) * (nums.length + 1) / 2
     * 我们在考虑缺失两个数字
     * 假设我们的数组为[1,4]，则缺失的数字为[2,3]
     * 我们可以得到缺失两个数字的和
     * 时间复杂度为O(n)，空间复杂度为O(1)
     * @param nums 数组
     * @return 返回缺失的两个数字
     */
    public int[] missingTwo(int[] nums) {
        int[] ans = new int[2];
        //可以求出两个数字之和
        int upper = nums.length + 2;
        int sum = (upper + 1) * upper / 2;
        for (int num : nums) {
            sum -= num;
        }
        //sum代表两个数字的和
        //其中一个数小于sum / 2，一个数大于sum / 2
        //当sum为奇数时，有一个数可能等于limit
        int limit = sum / 2;
        int tem = 0;
        //对于小于等于limit的数求和
        for (int num : nums) {
            if (num <= limit) {
                tem += num;
            }
        }
        //为等差数列
        int smallerOne = (1 + limit) * limit / 2 - tem;
        int biggerOne = sum - smallerOne;
        ans[0] = smallerOne;
        ans[1] = biggerOne;
        return ans;
    }

    public static int missingOne(int[] nums) {
        int upper = nums.length + 1;
        int sum = (1 + upper) * upper / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3};
        System.out.println(missingOne(nums));
    }
}
