package LeetCode;

public class _798 {
    /**
     * 给你一个数组nums，我们可以将它按一个非负整数 k 进行轮调，
     * 这样可以使数组变为[nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]\的形式。
     * 此后，任何值小于或等于其索引的项都可以记作一分。
     *
     *
     * 对于数组Nums中的元素x，满足条件的下标为[x, n - 1]，有n - x个下标
     * 假设x的初始下标为i,当轮调为k时,x的下标为(i - k + n) % n
     * 如果x能得分，则  (i - k + n) % n >= x   k <= (i - x + n) % n
     * (i - k + n) % n <= n - 1     k >= (i + 1) % n
     * 如果 i >= x 则   k <= i - x 或 k >= i + 1
     * 如果 i < x 则    k <= i - x + n 且 k >= i + 1
     *
     *
     * point[k]代表轮调为k时的得分。
     * diff[k] = point[k] - point[k - 1]
     *
     * @param nums
     * @return
     */
    public static int bestRotation(int[] nums) {
        int length = nums.length;
        int[] diff =  new int[length];
        for(int i = 0; i < length; i++) {
            int low = (i + 1) % length;
            int high = (i - nums[i] + length + 1) % length;
            diff[low] ++;
            diff[high] --;
            if(low >= high)
                diff[0] ++;
        }
        int max = diff[0];
        int sum = diff[0];
        int ans = 0;
        for(int i = 1; i < length; i ++) {
            sum = diff[i] + sum;
            if(max < sum) {
                max = sum;
                ans = i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] test = new int[] {2, 3, 1, 4, 0};
        bestRotation(test);
    }
}
