package bytedance.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class 无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int start = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, i);
            } else {
                // 如果当前字符已经存在于前面，要维持最大
                start = Math.max(start, map.get(ch));
                map.put(ch, i);
            }
            ans = Math.max(ans, i - start);
        }
        return ans;
    }
}
