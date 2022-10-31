package LeetCode.BinarySearch;

import java.util.HashSet;
import java.util.Set;

public class _1898 {
    public int maximumRemovals(String s, String p, int[] removable) {
        int k = removable.length;
        int low = 1;
        int high = k;
        int mid;
        int res = 0;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (check(s, p, mid, removable)) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public boolean check(String s, String p, int preK, int[] removable) {
        int len = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < preK; i++) {
            set.add(removable[i]);
        }
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(i)) {
                continue;
            } else {
                if (s.charAt(i) == p.charAt(len)) {
                    len++;
                    if (len == p.length()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
