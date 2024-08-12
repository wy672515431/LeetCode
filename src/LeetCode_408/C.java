package LeetCode_408;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class C {
    public static void main(String[] args) {
        System.out.println(new C().numberOfSubstrings("000011110"));
    }

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] ones = new int[n + 1];
        // 我们还要记录当前0的上一个0的位置，这样我们就可以知道当前0之前有多少个1
        Map<Integer, Integer> map = new HashMap<>();
        // 1的上一个0的位置
        Map<Integer, Integer> map1 = new HashMap<>();
        int curZero = -1;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '1') {
                ones[i + 1] = ones[i] + 1;
                map1.put(i, curZero);
            } else {
                ones[i + 1] = ones[i];
                map.put(i, curZero);
                curZero = i;
            }
        }
        int[] dp = new int[n];
        dp[0] = s.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
            char ch = s.charAt(i);
            int cnt = 1;
            if (ch == '1') {
                int preZero = map1.get(i);
                if (preZero == -1) {
                    dp[i] = ones[i + 1] - ones[preZero + 1];
                    continue;
                }
                dp[i] += ones[i + 1] - ones[preZero + 1];
                int cur = preZero;
                preZero = map.get(cur);
                while (preZero != -1 && cnt <= 200) {
                    int cntOne = ones[i + 1] - ones[preZero + 1];
                    if (cntOne >= cnt * cnt) {
                        int cnt1 = ones[i + 1] - ones[cur + 1];
                        if (cnt1 >= cnt * cnt) {
                            // 算上0
                            dp[i] += ones[cur + 1] - ones[preZero + 1] + 1;
                        } else {
                            dp[i] += cntOne - cnt * cnt + 1;
                        }
                    }
                    cur = preZero;
                    preZero = map.get(cur);
                    cnt++;
                }
                int cntOne = ones[i + 1] - ones[preZero + 1];
                if (cntOne >= cnt * cnt) {
                    int cnt1 = ones[i + 1] - ones[cur + 1];
                    if (cnt1 >= cnt * cnt) {
                        dp[i] += ones[cur + 1] - ones[preZero + 1] + 1;
                    } else {
                        dp[i] += cntOne - cnt * cnt + 1;
                    }
                }
            } else {
                int cur = i;
                int preZero = map.get(cur);
                while (preZero != -1 && cnt <= 200) {
                    int cntOne = ones[i + 1] - ones[preZero + 1];
                    if (cntOne >= cnt * cnt) {
                        int cnt1 = ones[i + 1] - ones[cur + 1];
                        if (cnt1 >= cnt * cnt) {
                            dp[i] += ones[cur + 1] - ones[preZero + 1] + 1;
                        } else {
                            dp[i] += cntOne - cnt * cnt + 1;
                        }
                    }
                    cur = preZero;
                    preZero = map.get(cur);
                    cnt++;
                }
                int cntOne = ones[i + 1] - ones[preZero + 1];
                if (cntOne >= cnt * cnt) {
                    int cnt1 = ones[i + 1] - ones[cur + 1];
                    if (cnt1 >= cnt * cnt) {
                        dp[i] += ones[cur + 1] - ones[preZero + 1] + 1;
                    } else {
                        dp[i] += cntOne - cnt * cnt + 1;
                    }
                }
            }
        }
        return Arrays.stream(dp).sum();
    }
}
