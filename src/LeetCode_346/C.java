package LeetCode_346;

import java.util.HashSet;
import java.util.Set;

public class C {
    public static void main(String[] args) {
        C c = new C();
        c.check("1296", 36);
    }

    Set<Integer> leftVal = new HashSet<>();
    Set<Integer> rightVal = new HashSet<>();

    /**
     * precondition : for a given number i([1, n]), the string format of i * i could be
     * sliced into several substrings.The Integer value of these substrings sums up to i * i;
     * @param n
     * @return
     */
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            String square = String.valueOf(i * i);
            if (check(square, i)) {
                ans += i * i;
            }
        }
        return ans;
    }

    public boolean check(String square, int sqrt) {
        for (int i = 0; i < square.length(); i++) {
            leftVal.clear();
            rightVal.clear();
            String first = square.substring(0, i + 1);
            String second = i == square.length() - 1 ? "0" : square.substring(i + 1);
            dfs(first, 0, 0);
            dfs1(second, 0, 0);
            for (int val : leftVal) {
                if (rightVal.contains(sqrt - val)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void dfs(String str, int index, int sum) {
        if (index == str.length()) {
            leftVal.add(sum);
            return;
        }
        for (int i = index; i < str.length(); i++) {
            int val = Integer.parseInt(str.substring(index, i + 1));
            dfs(str, i + 1, sum + val);
        }
    }

    public void dfs1(String str, int index, int sum) {
        if (index == str.length()) {
            rightVal.add(sum);
            return;
        }
        for (int i = index; i < str.length(); i++) {
            int val = Integer.parseInt(str.substring(index, i + 1));
            dfs1(str, i + 1, sum + val);
        }
    }

}
