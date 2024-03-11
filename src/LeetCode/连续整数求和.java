package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class 连续整数求和 {
    public int consecutiveNumbersSum(int n) {
        return solve(n);
    }

    private int solve(int n) {
        int ans = 0;
        for (int i = 1; i  <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if ((i & 1) == 1) {
                    ans++;
                }
                if (n / i != i && ((n / i) & 1) == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> same = new HashSet<>();
        for (int i = 0; i < fronts.length; ++i) {
            if (fronts[i] == backs[i]) {
                same.add(fronts[i]);
            }
        }
        int res = 3000;
        for (int x : fronts) {
            if (x < res && !same.contains(x)){
                res = x;
            }
        }
        for (int x : backs) {
            if (x < res && !same.contains(x)) {
                res = x;
            }
        }
        return res % 3000;
    }
}
