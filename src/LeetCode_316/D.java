package LeetCode_316;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D {
    public long makeSimilar(int[] nums, int[] target) {
        long ans = 0L;
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                list.add(nums[i]);
            }
            if (target[i] % 2 == 0) {
                list1.add(target[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list1.get(i) >= list.get(i)) {
                ans += (list1.get(i) - list.get(i)) / 2;
            }
        }
        list.clear();
        list1.clear();
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) {
                list.add(nums[i]);
            }
            if (target[i] % 2 != 0) {
                list1.add(target[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list1.get(i) >= list.get(i)) {
                ans += (list1.get(i) - list.get(i)) / 2;
            }
        }
        return ans;
    }
}
