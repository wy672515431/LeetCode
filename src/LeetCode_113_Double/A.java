package LeetCode_113_Double;

import java.util.List;

public class A {
    public int minimumRightShifts(List<Integer> nums) {
        int cnt1 = 1;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) < nums.get(i + 1)) {
                cnt1++;
            } else {
                break;
            }
        }
        int cnt2 = cnt1 == nums.size() ? 0 : 1;
        for (int i = cnt1; i < nums.size() - 1; i++) {
            if (nums.get(i) < nums.get(i + 1)) {
                cnt2++;
            } else {
                break;
            }
        }
        if (cnt1 + cnt2 != nums.size()) {
            return -1;
        }
        if (nums.get(cnt1 - 1) < nums.get(cnt1 + cnt2 - 1)) {
            return -1;
        }
        return cnt2;
    }
}
