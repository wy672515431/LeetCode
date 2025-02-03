package LeetCode_142_Dobule;

public class A {
    public int possibleStringCount(String word) {
        int ans = 0;
        int n = word.length();
        int index = 0;
        while (index < n) {
            char ch = word.charAt(index);
            int nextIndex = index + 1;
            while (nextIndex < n) {
                char nch = word.charAt(nextIndex);
                if (nch != ch) {
                    break;
                }
                nextIndex++;
            }
            ans += nextIndex - index - 1;
            index = nextIndex;
        }
        return ans + 1;
    }
}
