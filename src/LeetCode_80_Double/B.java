package LeetCode_80_Double;

import java.util.Arrays;

public class B {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] nums = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            long target = (success % spells[i]) == 0 ? success / spells[i] : success / spells[i] + 1;
            int cnt = lowerBound(potions, target);
            nums[i] = m - cnt;
        }
        return nums;
    }

    /**
     * 返回大于等于target的下标
     * @param potions
     * @param target
     * @return
     */
    private int lowerBound(int[] potions, long target) {
        int low = 0;
        int high = potions.length - 1;
        if (potions[high] < target) {
            return high + 1;
        }
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (potions[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
