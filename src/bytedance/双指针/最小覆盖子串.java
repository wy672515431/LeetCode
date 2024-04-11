package bytedance.双指针;

import java.util.HashMap;
import java.util.Map;

public class 最小覆盖子串 {
    Map<Character, Integer> tMap = new HashMap<>();
    Map<Character, Integer> sMap = new HashMap<>();
    public String minWindow(String s, String t) {
        // 双指针和滑动窗口
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int l = 0, r = -1, slen = s.length();
        int ans = Integer.MAX_VALUE, sl = 0, sr = -1;
        while (r < slen) {
            r++;
            if (r < slen && tMap.containsKey(s.charAt(r))) {
                sMap.put(s.charAt(r), sMap.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 满足要求
            while (check() && l <= r) {
                if (ans > r - l + 1) {
                    ans = r - l + 1;
                    sl = l;
                    sr = r;
                }
                char ch = s.charAt(l);
                if (sMap.containsKey(ch)) {
                    sMap.put(ch, sMap.get(ch) - 1);
                }
                l++;
            }
        }
        return ans == Integer.MAX_VALUE ? "" : s.substring(sl, sr + 1);
    }

    private boolean check() {
        for (Map.Entry<Character, Integer> entries : tMap.entrySet()) {
            char key = entries.getKey();
            int val = entries.getValue();
            if (sMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
