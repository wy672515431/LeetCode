package LeetCode_346;

public class B {
    public String makeSmallestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char lch = s.charAt(l);
            char rch = s.charAt(r);
            sb.append(lch <= rch ? lch : rch);
            l++;
            r--;
        }
        ans.append(sb);
        if (l == r) {
            ans.append(s.charAt(l));
        }
        ans.append(sb.reverse());
        return ans.toString();
    }
}
