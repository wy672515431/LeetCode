package LeetCode_411;

public class A {
    public int countKConstraintSubstrings(String s, int k) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int cnt0 = 0, cnt1 = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '0') {
                    cnt0++;
                } else {
                    cnt1++;
                }
                if (cnt0 <= k || cnt1 <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
