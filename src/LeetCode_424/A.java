package LeetCode_424;

public class A {
    public int countValidSelections(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (check(nums, i, true)) {
                    ans++;
                }
                if (check(nums, i, false)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean check(int[] nums, int index, boolean isLeft) {
        int n = nums.length;
        int[] copy = new int[n];
        System.arraycopy(nums, 0, copy, 0, n);
        while (true) {
            if (index < 0 || index >= n) {
                break;
            }
            if (copy[index] > 0) {
                copy[index]--;
                isLeft = !isLeft;
            }
            if (isLeft) {
                index--;
            } else {
                index++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (copy[i] > 0) {
                return false;
            }
        }
        return true;
    }
}
