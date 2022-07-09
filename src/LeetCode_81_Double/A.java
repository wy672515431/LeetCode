package LeetCode_81_Double;

public class A {
    public int countAsterisks(String s) {
        boolean isInVerticalBars = false;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                isInVerticalBars = !isInVerticalBars;
            } else if (s.charAt(i) == '*' && !isInVerticalBars) {
                ans++;
            }
        }
        return ans;
    }
}
