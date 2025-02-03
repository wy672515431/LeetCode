package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 括号生成 {
    // 在每一个位置，左括号的数量必须大于等于右括号的数量
    StringBuilder sb = new StringBuilder();
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        solve(0, 0, n);
        return ans;
    }

    private void solve(int lParen, int rParen, int n) {
        if (lParen > n) {
            return;
        }

        if (rParen > n) {
            return;
        }

        if (rParen > lParen) {
            return;
        }

        if (lParen == n && rParen == n) {
            ans.add(sb.toString());
            return;
        }

        // (
        sb.append('(');
        solve(lParen + 1, rParen, n);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        solve(lParen, rParen + 1, n);
        sb.deleteCharAt(sb.length() - 1);
    }

}
