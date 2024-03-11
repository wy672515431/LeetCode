package bytedance.双指针;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 找到字符串中所有字母异位词 {
    Map<Character, Integer> pMap = new HashMap<>();
    Map<Character, Integer> sMap = new HashMap<>();
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
        }
        int l = 0, r = -1;
        while (r < s.length()) {
            r++;
            // 添加
            if (r < s.length()) {
                char ch = s.charAt(r);
                sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            }
            if (r - l + 1 == p.length()) {
                if (check()) {
                    ans.add(l);
                }
                if (l < s.length()) {
                    // 删除
                    char ch = s.charAt(l);
                    sMap.put(ch, sMap.get(ch) - 1);
                    l++;
                }
            }
        }
        return ans;
    }

    private boolean check() {
        for (Map.Entry<Character, Integer> entry: pMap.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if (sMap.getOrDefault(key, 0) != val) {
                return false;
            }
        }
        return true;
    }
}
