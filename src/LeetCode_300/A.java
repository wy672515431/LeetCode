package LeetCode_300;

import java.util.HashMap;

public class A {
    public String decodeMessage(String key, String message) {
        char ch = 'a';
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < key.length(); i++) {
            if (map.get(key.charAt(i)) == null && key.charAt(i) != ' ') {
                map.put(key.charAt(i), ch);
                ch++;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                ans.append(message.charAt(i));
            } else {
                ans.append(map.get(message.charAt(i)));
            }
        }
        return ans.toString();
    }
}
