package CF.CF_971_DIV4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        while (t-- > 0) {
            String[] input = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            int low = 1, high = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (calc(mid, n, k) >= 0) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
//             low 和 low - 1 都是可能的答案，取最小的
            System.out.println(Math.min(Math.abs(calc(low, n, k)), Math.abs(calc(low - 1, n, k))));
        }
        bufferedReader.close();
    }

    private static long calc(long x, long n, long k) {
        return x * x + (2 * k - 1) * x - n * k - n * (n - 1) / 2;
    }
}
