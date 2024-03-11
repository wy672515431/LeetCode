package bytedance.字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                count[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.repeat('a' + i, count[i]);
            }
            List<String> temp = map.getOrDefault(sb.toString(), new ArrayList<>());
            temp.add(str);
            map.put(sb.toString(), temp);
        }
        return new ArrayList<>(map.values());
    }
}
