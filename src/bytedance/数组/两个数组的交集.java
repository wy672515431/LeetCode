package bytedance.数组;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }
        int[] res = new int[set2.size()];
        int cnt = 0;
        for (int num : set2) {
            res[cnt++] = num;
        }
        return res;
    }
}
