package LeetCode_298;

public class A {
    public String greatestLetter(String s) {
        boolean[] lowerAlpha = new boolean[26];
        boolean[] upperAlpha = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(ch)) {
                upperAlpha[ch - 'A'] = true;
            } else {
                lowerAlpha[ch - 'a'] = true;
            }
        }
        for (int i = 25; i >=0; i--) {
            if (lowerAlpha[i] && upperAlpha[i]) {
                char ch = (char)(i + 'A');
                return new StringBuilder().append(ch).toString();
            }
        }
        return "";
    }
}
