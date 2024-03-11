package LeetCode;

public class _1144 {
    public static int movesToMakeZigzag(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        // 枚举偶数
        // 枚举奇数
        int[] tem = new int[length];
        System.arraycopy(nums, 0, tem, 0, length);
        int evenAns = 0;
        for (int i = 0; i < length; i+=2) {
            if (i - 1 >= 0) {
                int diff = tem[i] - tem[i - 1] <= 0 ? (tem[i - 1] - tem[i] + 1) : 0;
                tem[i - 1] += diff;
                evenAns += diff;
            }
            if (i + 1 < length) {
                int diff = tem[i] - tem[i + 1] <= 0 ? (tem[i + 1] - tem[i] + 1) : 0;
                tem[i + 1] -= diff;
                evenAns += diff;
            }
        }
        int oddAns = 0;
        for (int i = 1; i < length; i+=2) {
            if (i - 1 >= 0) {
                int diff = nums[i] - nums[i - 1] <= 0 ? (nums[i - 1] - nums[i] + 1) : 0;
                nums[i - 1] += diff;
                oddAns += diff;
            }
            if (i + 1 < length) {
                int diff = nums[i] - nums[i + 1] <= 0 ? (nums[i + 1] - nums[i] + 1) : 0;
                nums[i + 1] -= diff;
                oddAns += diff;
            }
        }
        return Math.min(evenAns, oddAns);
    }

    public static void main(String[] args) {
        int[] num = {10,4,4,10,10,6,2,3};
        movesToMakeZigzag(num);
    }
}
