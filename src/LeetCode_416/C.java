package LeetCode_416;

import java.util.HashMap;
import java.util.Objects;

public class C {
    HashMap<Character, Integer> map2 = new HashMap<>();
    HashMap<Character, Integer> map1 = new HashMap<>();
    public long validSubstringCount(String word1, String word2) {
        for (int i = 0; i < word2.length(); i++) {
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);
        }
        int less = map2.size();
        int l = 0, r = -1, n = word1.length();
        int cur = 0, prev = 0;
        long res = 0;
        while (r < n) {
            r++;
            if (r < n && map2.containsKey(word1.charAt(r))) {
                map1.put(word1.charAt(r), map1.getOrDefault(word1.charAt(r), 0) + 1);
                if (Objects.equals(map1.get(word1.charAt(r)), map2.get(word1.charAt(r)))) {
                    less--;
                }
            }
            int sum = 0;
            while (less == 0 && l <= r) {
                sum += 1;
                char ch = word1.charAt(l);
                if (map1.containsKey(ch)) {
                    map1.put(ch, map1.get(ch) - 1);
                    if (map1.get(ch) < map2.get(ch)) {
                        less++;
                    }
                }
                l++;
            }
            if (r < n) {
                cur = sum;
                cur += prev;
                res += cur;
                prev = cur;
                cur = 0;
            }
        }
        return res;
    }
}
