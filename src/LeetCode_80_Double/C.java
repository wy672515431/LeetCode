package LeetCode_80_Double;

import java.util.HashMap;
import java.util.HashSet;

public class C {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        // sub -> s
        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        for (int i = 0; i < mappings.length; i++) {
            if (map.get(mappings[i][0]) == null) {
                HashSet<Character> set = new HashSet<>();
                map.put(mappings[i][0], set);
                set.add(mappings[i][1]);
            } else {
                map.get(mappings[i][0]).add(mappings[i][1]);
            }
        }
        int n = s.length();
        int m = sub.length();
        for (int i = 0; i < n && i + m - 1 < n; i++) {
            boolean ok = true;
            for (int j = 0; j < m; j++) {
                if (sub.charAt(j) != s.charAt(i + j)) {
                    if (map.get(sub.charAt(j)) == null ||
                            !map.get(sub.charAt(j)).contains(s.charAt(i + j))) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
