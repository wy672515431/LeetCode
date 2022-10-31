package LeetCode_309;

import java.util.HashMap;

public class A {
    public boolean checkDistances(String s, int[] distance) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer val = map.putIfAbsent(ch, i);
            if (val != null) {
                val = map.get(ch);
                if (i - val - 1 != distance[ch - 'a']) {
                    return false;
                }
            }
        }
        return true;
    }
}
