package LeetCode.BinarySearch;

public class 袋子里最少数目的球 {
    //求最大值的最小值
    public int minimumSize(int[] nums, int maxOperations) {
        //最大值的下界至少为1
        int low = 1;
        //最大值的上界为数组中的最大值
        int high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (check(nums, maxOperations, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public boolean check(int[] nums, int maxOperations, int value) {
        int minOfMax = value;
        int sumOfOperations = 0;
        for (int num : nums) {
            if (num > minOfMax) {
                int operation = num % minOfMax == 0 ? (num / minOfMax - 1) : (num / minOfMax);
                sumOfOperations += operation;
            }
        }
        if (sumOfOperations > maxOperations) {
            return false;
        }
        return true;
    }
}
