package CF.CF_974_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(bf.readLine());
            long[] wealth = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            Arrays.sort(wealth);
            long sum = Arrays.stream(wealth).sum();
            long ans = -1;
            long low = 0, high = (long)1e18;
            while (low <= high) {
                long mid = (high - low) / 2 + low;
                if (check(wealth, mid, sum)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            System.out.println(ans);
        }
        bf.close();
    }

    private static boolean check(long[] wealth, long x, long sum) {
        double avg = (double)(sum + x) / wealth.length;
        int cnt = 0;
        for (int i = 0; i < wealth.length; i++) {
            if (i == wealth.length - 1) {
                if (wealth[i] + x < avg / 2) {
                    cnt++;
                }
                continue;
            }
            if (wealth[i] < avg / 2) {
                cnt++;
            }
        }
        return cnt > wealth.length / 2;
    }
}
