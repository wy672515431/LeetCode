package LeetCode_420;

import java.util.HashMap;
import java.util.Map;

public class B {
    public int numberOfSubstrings(String s, int k) {
        Map<Character, Integer> cntMap = new HashMap<>();
        int l = 0, r = -1, n = s.length();
        int ans = 0;
        while (r < n) {
            r++;
            if (r < n) {
                cntMap.put(s.charAt(r), cntMap.getOrDefault(s.charAt(r), 0) + 1);
                while (check(cntMap, k)) {
                    ans += n - r;
                    cntMap.put(s.charAt(l), cntMap.get(s.charAt(l)) - 1);
                    l++;
                }
            }
        }
        return ans;
    }

    private boolean check(Map<Character, Integer> cntMap, int k) {
        for (int cnt : cntMap.values()) {
            if (cnt >= k) {
                return true;
            }
        }
        return false;
    }
}
