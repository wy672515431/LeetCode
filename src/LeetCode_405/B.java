package LeetCode_405;

import java.util.ArrayList;
import java.util.List;

public class B {
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> validStrings(int n) {
        solve(0, n);
        return ans;
    }

    private void solve(int cur, int n) {
        if (cur == n) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i <= 1; i++) {
            if (cur != 0) {
                // 看前面一个字符是否为1
                if (sb.charAt(cur - 1) == '0' && i == 0) {
                    continue;
                }
            }
            sb.append(i);
            solve(cur + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
