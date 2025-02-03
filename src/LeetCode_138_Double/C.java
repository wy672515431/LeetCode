package LeetCode_138_Double;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class C {
    char[] num;
    Set<Long> set = new HashSet<>();

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.countGoodIntegers(10, 1));
    }

    // 计算组合数
    private long comb(int n, int m) {
        long ans = 1L;
        for (int i = 1; i <= m; i++) {
            ans = ans * (n - i + 1) / i;
        }
        return ans;
    }

    public long countGoodIntegers(int n, int k) {
        num = new char[n];
        dfs(n, k, 0);
        long ans = 0L;
        for (long x : set) {
            int[] cnt = new int[10];
            int i = 0;
            while (x > 0) {
                cnt[i++] = (int) (x % 11);
                x /= 11;
            }
            int cur = n;
            long sum = 1L;
            for (i = 0; i < 10; i++) {
                if (cnt[i] == 0) {
                    continue;
                }
                if (i == 0) {
                    sum *= comb(cur - 1, cnt[i]);
                } else {
                    sum *= comb(cur, cnt[i]);
                }
                cur -= cnt[i];
            }
            ans += sum;
        }
        return ans;
    }

    // 构建回文数
    private void dfs(int n, int k, int cur) {
        //  [i, n - i - 1]
        if (cur == 0) {
            for (int i = 1; i <= 9; i++) {
                num[cur] = (char) ('0' + i);
                num[n - cur - 1] = (char) ('0' + i);
                dfs(n, k, cur + 1);
            }
            return;
        }
        if (cur < n - cur - 1) {
            for (int i = 0; i <= 9; i++) {
                num[cur] = (char) ('0' + i);
                num[n - cur - 1] = (char) ('0' + i);
                dfs(n, k, cur + 1);
            }
        } else if (cur == n - cur - 1) {
            for (int i = 0; i <= 9; i++) {
                num[cur] = (char) ('0' + i);
                num[n - cur - 1] = (char) ('0' + i);
                dfs(n, k, cur + 1);
            }
        } else {
            long val = Long.parseLong(new String(num));
            if (val % k == 0) {
                int[] cnt = new int[10];
                for (int i = 0; i < n; i++) {
                    cnt[num[i] - '0']++;
                }
                long sum = 0L;
                long mul = 1L;
                for (int i = 0; i < 10; i++) {
                    sum += cnt[i] * mul;
                    mul *= 11;
                }
                set.add(sum);
            }
        }
    }
}
