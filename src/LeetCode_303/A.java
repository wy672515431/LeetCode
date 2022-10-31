package LeetCode_303;

public class A {
    public char repeatedCharacter(String s) {
        char[] chs = new char[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            chs[ch - 'a']++;
            if (chs[ch - 'a'] == 2) {
                return ch;
            }
        }
        return '1';
    }
}
