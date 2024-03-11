package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 括号生成 {
    // 在每一个位置，左括号的数量必须多余右括号的数量
    static StringBuilder sb = new StringBuilder();
    static List<String> ans = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        solve(0, 0, n);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    private static void solve(int lParen, int rParen, int n) {
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
