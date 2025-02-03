package LeetCode_412;

public class B {
    public int countPairs(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(nums[i], nums[j]) || check(nums[j], nums[i])) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 交换a或b的某一数位
     * @param a
     * @param b
     * @return
     */
    private boolean check(int a, int b) {
        if (a == b) {
            return true;
        }
        char[] chs = String.valueOf(a).toCharArray();
        int len = chs.length;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                // 交换a的第i位和第j位, 看是否等于b
                if (chs[i] != chs[j]) {
                    char tmp = chs[i];
                    chs[i] = chs[j];
                    chs[j] = tmp;
                    if (Integer.parseInt(new String(chs)) == b) {
                        return true;
                    }
                    tmp = chs[i];
                    chs[i] = chs[j];
                    chs[j] = tmp;
                }
            }
        }
        return false;
    }
}
