package CF.CF_971_DIV4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        while (t-- > 0) {
            String[] input = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int q = Integer.parseInt(input[1]);
            int[] arr = new int[n];
            String[] arrInput = bufferedReader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(arrInput[i]);
            }
            long[] pre = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                pre[i] = pre[i - 1] + arr[i - 1];
            }
            for (int i = 0; i < q; i++) {
                String[] query = bufferedReader.readLine().split(" ");
                long l = Long.parseLong(query[0]);
                long r = Long.parseLong(query[1]);
                System.out.println(query(r, n, pre) - query(l - 1, n, pre));
            }
        }
        bufferedReader.close();
    }

    private static long query(long x, int n, long[] pre) {
        long cnt = x / n;
        long ans = 0L;
        ans += cnt * pre[n];
        long mod = x % n;
        // 第cnt个排列的第一个数为cnt
        // 1 2 3 2 3 1 3 1 2
        if (mod > 0) {
            if (cnt + mod <= n) {
                ans += pre[(int)(cnt + mod)] - pre[(int)cnt];
            } else {
                ans += pre[n] - pre[(int)cnt];
                ans += pre[(int)(cnt + mod - n) % n];
            }
        }
        return ans;
    }
}
