package bytedance.字符串;

public class 旋转字符串 {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        String s1 = s + s;
        // kmp
        int n = s1.length();
        int m = goal.length();
        // find m is the substring of n
        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && goal.charAt(i) != goal.charAt(j)) {
                j = next[j - 1];
            }
            if (goal.charAt(i) == goal.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s1.charAt(i) != goal.charAt(j)) {
                j = next[j - 1];
            }
            if (s1.charAt(i) == goal.charAt(j)) {
                j++;
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }
}
