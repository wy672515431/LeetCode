package LeetCode_80_Double;

public class A {
    public boolean strongPasswordCheckerII(String password) {
        boolean hasEightCh = false;
        boolean hasLowLetter = false;
        boolean hasHighLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialCh = false;
        boolean hasConsistentCh = false;
        if (password.length() >= 8) {
            hasEightCh = true;
        }
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLowLetter = true;
            }
            else if (Character.isUpperCase(ch)) {
                hasHighLetter = true;
            }
            else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            else {
                hasSpecialCh = true;
            }
            if (i != password.length() - 1) {
                char nextCh = password.charAt(i + 1);
                if (ch == nextCh) {
                    hasConsistentCh = true;
                }
            }
        }
        return hasEightCh && hasLowLetter &&
                hasHighLetter && hasDigit && hasSpecialCh && !hasConsistentCh;
    }
}
